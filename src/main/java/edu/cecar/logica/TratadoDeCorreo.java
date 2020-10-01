/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.logica;

import edu.cecar.persistencia.ConexionSql;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.swing.JOptionPane;

/**
 *
 * @author oderb
 */
public class TratadoDeCorreo {

    private static final String ALPHA_NUME_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String code;

    public static String codigoDeVerificacion() {
        StringBuilder builder = new StringBuilder();

        for (int i = 7; i > 0; i--) {
            int character = (int) (Math.random() * ALPHA_NUME_STRING.length());
            builder.append(ALPHA_NUME_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static boolean validarEmail(String input) {

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher comparador = emailpat.matcher(input);
        return comparador.find();

    }

    public static void EnviarCorreo(String emailReceptor, boolean opcion) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");

        String Email = "kindom.kg@gmail.com";
        String pass = "godgoulchajose12";

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Email, pass);
            }

        });

        ConexionSql.Conectar();
        if (opcion) {
            ConexionSql.obtenerCorreo().forEach(e -> {
                
                Message mensaje = mensajeParaEnviar(session, Email, e.getCorreo());
                try {
                    Transport.send(mensaje);
                } catch (MessagingException ex) {
                    Logger.getLogger(TratadoDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } else {
            Message mensaje = mensajeParaVerificar(session, Email, emailReceptor);
            Transport.send(mensaje);
        }
        System.out.println("el mensaje se envio correctamente");

    }

    private static Message mensajeParaEnviar(Session session, String emailEnvio, String emailReceptor) {

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(emailEnvio));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));

            mensaje.setSubject("con mucho cariño ronald");
            mensaje.setText("ronald ");
            return mensaje;
        } catch (Exception ex) {
            Logger.getLogger(TratadoDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Message mensajeParaVerificar(Session session, String emailEnvio, String emailReceptor) {
        try {
            code = codigoDeVerificacion();

            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(emailEnvio));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));

            mensaje.setSubject("Mensaje de verificacion");
            String htmlCode = "<h2>"
                    + "Enter this code in your App:<br/>"
                    + code
                    + "</h2>";
            mensaje.setContent(htmlCode, "text/html");

            return mensaje;

        } catch (Exception ex) {
            Logger.getLogger(TratadoDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void VerificarCorreo(String codigo, String email) {
        ConexionSql.Conectar();
        String cod;
        cod = JOptionPane.showInputDialog("Ingrese codigo de verificacion:");
        do {
            if (cod.equals(codigo)) {
                System.out.println("El codigo similar");
                ConexionSql.guardarCorreo(email);

            } else {
                System.out.println("No concuerda el correo");
            }
        } while (!cod.equals(codigo));

    }

    public static void main(String[] args) throws Exception {

        //EnviarCorreo("kindom.kg@gmail.com", false);
        System.out.println(code);
        //VerificarCorreo(code, "jose.chagui@cecar.edu.co");

    }

}
