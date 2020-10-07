/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.main;

import static edu.cecar.logica.ManejoExcel.guardarEnExcel;
import static edu.cecar.logica.ScrappingPDF.mostrarEnConsola;
import edu.cecar.logica.TratadoDeCorreo;
import static edu.cecar.logica.TratadoDeCorreo.EnviarCorreo;
import static edu.cecar.logica.TratadoDeCorreo.validarEmail;
import edu.cecar.persistencia.ConexionSql;
import static edu.cecar.persistencia.ConexionSql.EliminarCorreo;
import static edu.cecar.persistencia.ConexionSql.obtenerCorreo;
import edu.cecar.persistencia.Correo;
import javax.swing.JOptionPane;

/**
 *
 * @author oderb
 */
public class Main {

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

    public static void main(String[] args) throws Exception {
        ConexionSql.Conectar();
        boolean d = false;
        boolean verificacion;
        
        String nuevoCorreo;
        while (true) {
            switch (JOptionPane.showInputDialog("DIGITE LA OPCION:\na. Guardar Correo\nb. Borrar Correo\nc. Mostrar Datos Extraidos.\nd. Enviar Correos.")) {

                case "a":
                    do {

                        nuevoCorreo = JOptionPane.showInputDialog("Digite el correo que desea guardar: ");

                        if (validarEmail(nuevoCorreo)) {
                            JOptionPane.showMessageDialog(null, "Momento de Verificar correo, pendiente a tu correo!");

                            do {
                                code = codigoDeVerificacion();
                                TratadoDeCorreo.EnviarCorreo(code, nuevoCorreo, false);
                                verificacion = TratadoDeCorreo.VerificarCorreo(code, nuevoCorreo);
                                if (verificacion == false) {
                                    nuevoCorreo = JOptionPane.showInputDialog("Digite el correo que desea guardar: ");
                                    if (validarEmail(nuevoCorreo)) {
                                        d = true;
                                    } else {
                                        d = false;
                                    }
                                } else {
                                    d = false;
                                }
                            } while (d == true);
                        } else {
                            JOptionPane.showMessageDialog(null, "el Correo esta mal escrito porfavor vuelva a intentarlo");
                        }
                    } while (validarEmail(nuevoCorreo) == false);

                    break;
                case "b":

                    Correo correo = (Correo) JOptionPane.showInputDialog(null, "Elegir Correo que desea borrar",
                            "Seleccion", JOptionPane.QUESTION_MESSAGE, null, obtenerCorreo().toArray(), null);
                    EliminarCorreo(correo.getCorreo());

                    break;

                case "c":
                    mostrarEnConsola();

                    break;
                case "d":
                    guardarEnExcel();
                    EnviarCorreo(null,null,true);                  
                    break;

            }
        }

    }

}
