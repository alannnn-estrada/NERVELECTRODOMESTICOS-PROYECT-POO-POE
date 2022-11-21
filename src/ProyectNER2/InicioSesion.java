/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectNER2;
import java.awt.Color;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Alumno16SC
 */
public class InicioSesion extends javax.swing.JFrame {

    /**
     * Creates new form InicioSesion
     */
    public InicioSesion() {
        initComponents();
        this.getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null); //* Para todas las ventanas ///* locacion al centro de la pantalla
        setIconImage(new ImageIcon(getClass().getResource("/ProyectNER2/icon/IconWindow.png")).getImage()); //*Icono del programa 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfusu = new javax.swing.JTextField();
        tpf_pass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIngreso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio Sesion");

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(82, 208, 83));
        jLabel1.setText("Bienvenido, inicie sesion para acceder al sistema");

        tfusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfusuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(82, 208, 83));
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(82, 208, 83));
        jLabel3.setText("Contraseña");

        btnIngreso.setBackground(new java.awt.Color(102, 102, 255));
        btnIngreso.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnIngreso.setForeground(new java.awt.Color(255, 255, 255));
        btnIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/listo-removebg-preview.png"))); // NOI18N
        btnIngreso.setText("Ingresar");
        btnIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(btnIngreso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tpf_pass)
                            .addComponent(tfusu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tpf_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(34, 34, 34)
                .addComponent(btnIngreso)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void tfusuActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    @SuppressWarnings("deprecation")
    private void btnIngresoActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        String Usuario, Contraseña, User,Pass;
        User="admin";
        Pass="admin";
        String USERR;
        Usuario=tfusu.getText();
        Contraseña=tpf_pass.getText();
        if (Usuario.equals("") || Contraseña.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese campos validos");
        }
        if(Usuario.equals(User) && Contraseña.equals(Pass)){
            MenuAdmin MA= new MenuAdmin();
            MA.setVisible(true);
            dispose();
        }else{
            conectar cc= new conectar();
            Connection cn=cc.conexion();
            String sql;
            sql="SELECT Usuario,Contraseña FROM usuario WHERE Usuario='"+Usuario+"' AND Contraseña ='"+Contraseña+"'";
            try{
                PreparedStatement psd;
                ResultSet rs;
                psd= cn.prepareStatement(sql);
                rs=psd.executeQuery();
                if (rs.next()) {
                    USERR = rs.getString("Usuario");
                    JOptionPane.showMessageDialog(null, "Hola "+USERR);
                    MenuPrincipal MP= new MenuPrincipal();
                    MP.setVisible(true);
                    dispose();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Algo ocurrio, informar al admin");
            }
            
        }
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnIngreso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfusu;
    private javax.swing.JPasswordField tpf_pass;
    // End of variables declaration                   
}