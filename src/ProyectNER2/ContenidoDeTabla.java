/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectNER2;

import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
//*import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
/**
 *
 * @author alanj
 */
import javax.swing.table.DefaultTableModel;

public class ContenidoDeTabla extends javax.swing.JFrame {

    /**
     * Creates new form ContenidoDetablas
     */  
    conectar cc = new conectar();
    Connection cn =cc.conexion();
    DefaultTableModel model;
    public ContenidoDeTabla() {
        initComponents();
        this.getContentPane().setBackground(Color.gray);
        setLocationRelativeTo(null); //* Para todas las ventanas
        setIconImage(new ImageIcon(getClass().getResource("/ProyectNER2/icon/IconWindow.png")).getImage()); //*Icono del programa 
        cargartb1();
        cargartb2();
        cargartb3();
        cargartb4();
        cargartb5();
    }
    void cargartb1(){
        String [] titulos = {"ID","Cliente","Tipo","Marca","Cantidad","CostoTOT","CostoXPZ"};
        String [] registros = new String[8];
        
        String sqsl = "SELECT * FROM productos WHERE CONCAT (ID,'', Cliente,'', Tipo,'', Marca,'',Cantidad,'',CostoTOT,'',CostoXPZ)";
        
        model = new DefaultTableModel(null,titulos);
       
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqsl);
            
            while(rs.next()){
                registros[0]=rs.getString("ID");
                registros[1]=rs.getString("Cliente");
                registros[2]=rs.getString("Tipo");
                registros[3]=rs.getString("Marca");
                registros[4]=rs.getString("Cantidad");
                registros[5]=rs.getString("CostoTOT");
                registros[6]=rs.getString("CostoXPZ");
                
                model.addRow(registros);
            }
            tb_productos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    void cargartb2(){
        String [] titulos = {"ID","Tipo","Marca","Cantidad","TipoDaño","Comentario","Fecha"};
        String [] registros = new String[8];
        
        String sqsl = "SELECT * FROM reportes WHERE CONCAT (ID,'', Tipo,'', Marca,'', Cantidad,'',TipoDaño,'',Comentario,'',FechaGENE)";
        
        model = new DefaultTableModel(null,titulos);
       
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqsl);
            
            while(rs.next()){
                registros[0]=rs.getString("ID");
                registros[1]=rs.getString("Tipo");
                registros[2]=rs.getString("Marca");
                registros[3]=rs.getString("Cantidad");
                registros[4]=rs.getString("TipoDaño");
                registros[5]=rs.getString("Comentario");
                registros[6]=rs.getString("FechaGENE");
                
                model.addRow(registros);
            }
            tb_reportes.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    void cargartb3(){
        String [] titulos = {"IDTienda","NomCliente","Direccion","Telefono","Correo","RFC","NombreCargo"};
        String [] registros = new String[7];
        
        String sqsl = "SELECT * FROM clientestienda WHERE CONCAT (IDTienda,'', NomCliente,'', Direccion,'',Telefono,'',Correo,'',RFC,'',NombreCargo)";
        
        model = new DefaultTableModel(null,titulos);
       
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqsl);
            
            while(rs.next()){
                registros[0]=rs.getString("IDTienda");
                registros[1]=rs.getString("NomCliente");
                registros[2]=rs.getString("Direccion");
                registros[3]=rs.getString("Telefono");
                registros[4]=rs.getString("Correo");
                registros[5]=rs.getString("RFC");
                registros[6]=rs.getString("NombreCargo");
                model.addRow(registros);
            }
            tb_datosCliTiendas.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    void cargartb4(){
        String [] titulos = {"CveCliente","NomCliente","DireCliente","TelCliente","CorreoCliente","RFC"};
        String [] registros = new String[7];
        
        String sqsl = "SELECT * FROM clientes WHERE CONCAT (CveCliente,'', NomCliente,'', DireCliente,'',TelCliente,'',CorreoCliente,'',RFC)";
        
        model = new DefaultTableModel(null,titulos);
       
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqsl);
            
            while(rs.next()){
                registros[0]=rs.getString("CveCliente");
                registros[1]=rs.getString("NomCliente");
                registros[2]=rs.getString("DireCliente");
                registros[3]=rs.getString("TelCliente");
                registros[4]=rs.getString("CorreoCliente");
                registros[5]=rs.getString("RFC");
                
                model.addRow(registros);
            }
            tb_clientes.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    void cargartb5(){
        String [] titulos = {"ID","Usuario","Contraseña"};
        String [] registros = new String[7];
        
        String sqsl = "SELECT * FROM usuario WHERE CONCAT (Usuario,'', Contraseña)";
        
        model = new DefaultTableModel(null,titulos);
       
        
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqsl);
            
            while(rs.next()){
                registros[0]=rs.getString("ID");
                registros[1]=rs.getString("Usuario");
                registros[2]=rs.getString("Contraseña");
                
                
                model.addRow(registros);
            }
            tb_usuarios.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tb_productos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_reportes = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_datosCliTiendas = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_usuarios = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_clientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb_productos.setBackground(java.awt.Color.gray);
        tb_productos.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        tb_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tb_productos);

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(82, 208, 83));
        jLabel1.setText("Estas tablas solo se pueden visualizar, para hacer cambios inicie como usuario");

        tb_reportes.setBackground(java.awt.Color.gray);
        tb_reportes.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        tb_reportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_reportes);

        tb_datosCliTiendas.setBackground(java.awt.Color.gray);
        tb_datosCliTiendas.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        tb_datosCliTiendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tb_datosCliTiendas);

        tb_usuarios.setBackground(java.awt.Color.gray);
        tb_usuarios.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        tb_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tb_usuarios);

        tb_clientes.setBackground(java.awt.Color.gray);
        tb_clientes.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        tb_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tb_clientes);

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(82, 208, 83));
        jLabel2.setText("Contenido De Tablas");

        btnSalir.setBackground(new java.awt.Color(102, 102, 255));
        btnSalir.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/1233.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(596, 596, 596))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(340, 340, 340))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(365, 365, 365))
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSalir))
        );

        pack();
    }// </editor-fold>                        

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        MenuAdmin home= new MenuAdmin();
        home.setVisible(true);
        dispose();
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
            java.util.logging.Logger.getLogger(ContenidoDeTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContenidoDeTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContenidoDeTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContenidoDeTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContenidoDeTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tb_clientes;
    private javax.swing.JTable tb_datosCliTiendas;
    private javax.swing.JTable tb_productos;
    private javax.swing.JTable tb_reportes;
    private javax.swing.JTable tb_usuarios;
    // End of variables declaration                   
}

