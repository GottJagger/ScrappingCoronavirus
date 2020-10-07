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
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author oderb
 */
public class TratadoDeCorreo {

    

    public static boolean validarEmail(String input) {

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailpat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher comparador = emailpat.matcher(input);
        return comparador.find();

    }

    public static void EnviarCorreo(String codigo,String emailReceptor, boolean opcion) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");

        String Email = "scrapp.pdf.java@gmail.com";
        String pass = "Scrapp123";

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Email, pass);
            }

        });

        //ConexionSql.Conectar();
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
            Message mensaje = mensajeParaVerificar(session, Email, emailReceptor,codigo);
            Transport.send(mensaje);
        }
        System.out.println("el mensaje se envio correctamente");

    }

    private static Message mensajeParaEnviar(Session session, String emailEnvio, String emailReceptor) {

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(emailEnvio));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));

            mensaje.setSubject("Documento Datos Coronavirus");
            BodyPart texto = new MimeBodyPart();
            texto.setText("Se extrajo la informacion de los PDF\nY se guardo en el siguiente excel:");
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("./excel/plantilla.xlsx")));
            adjunto.setFileName("CASOS COVID.xlsx");

            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            mensaje.setContent(multiParte);
            return mensaje;
        } catch (Exception ex) {
            Logger.getLogger(TratadoDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Message mensajeParaVerificar(Session session, String emailEnvio, String emailReceptor,String codigo) {
        try {
            

            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(emailEnvio));
            mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptor));

            mensaje.setSubject("Mensaje de verificacion");
            String htmlCode = "<h2>"
                    + "Enter this code in your App:<br/>"
                    + codigo
                    + "</h2>";
            mensaje.setContent(htmlCode, "text/html");

            return mensaje;

        } catch (Exception ex) {
            Logger.getLogger(TratadoDeCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean VerificarCorreo(String codigo, String email) {
        //ConexionSql.Conectar();
        String cod;
        boolean d;
        cod = JOptionPane.showInputDialog("Ingrese codigo de verificacion:");
        if(cod==null){
            JOptionPane.showMessageDialog(null, "El correo no fue Verificado\n "
                    + "lo mas probable es que el correo no exista");
            cod="null";
        }
        
            if (cod.equals(codigo)) {
                d=true;
                ConexionSql.guardarCorreo(email);

            } else {
                d=false;
                
            }
        
            

        return d;
    }

}
