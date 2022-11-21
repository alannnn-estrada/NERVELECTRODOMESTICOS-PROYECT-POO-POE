/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectNER2;


import java.awt.Color;
import java.sql.*;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import javax.swing.ImageIcon;
import javax.swing.*;
/*import java.util.logging.Level;
import java.util.logging.Logger;*/
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;*/

/**
 *
 * @author alanj
 */
public class Reporte extends javax.swing.JFrame {

    /**
     * Creates new form MAIN
     */
    
    String ID,TIMESTAMP;
    conectar cc = new conectar();
    Connection cn =cc.conexion();
    DefaultTableModel model;
    public Reporte() {
        initComponents();
        this.getContentPane().setBackground(Color.gray);
        //*Crear si el producto ya esta en la lista agregarlo
        limpiar();
        desbloquear();
        cargar("");
        RellenarCBOXTipos("productos","Tipo",cbtipo);
        RellenarCBOXMarca("productos","Marca", cbmarca);
        setLocationRelativeTo(null); //* Para todas las ventanas
        setIconImage(new ImageIcon(getClass().getResource("/ProyectNER2/icon/IconWindow.png")).getImage()); //*Icono del programa 
    }
    
    public void RellenarCBOXTipos(String tabla, String valor, JComboBox combo){
        String sql;
        sql="SELECT * FROM "+tabla;
        Statement st;
        ResultSet rs;
        //*Pasar datos de tabla carrito a pedidos
        try{
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbtipo.addItem(rs.getString(valor));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en MYSQL");
        }
        
    }
    
     public void validacionCaracteres(java.awt.event.KeyEvent evento) {
        if (evento.getKeyChar() >=33 && evento.getKeyChar()<=64 || 
                (evento.getKeyChar() >=91 && evento.getKeyChar() <=96)|| 
                (evento.getKeyChar() >=123 && evento.getKeyChar() <=208)|| 
                (evento.getKeyChar() >=210 && evento.getKeyChar() <=240)|| 
                (evento.getKeyChar() >=242 && evento.getKeyChar() <=255)){
            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite caracteres especiales");
        }
        //*System.out.println("soy la letra "+evento.getKeyChar()+" y tengo el numero ASCII "+evento.getKeyChar() + 0);
    }
     
    public void validarCamposVacios(){
        if (t_cantidad.getText().isEmpty()){
           lbcantidad.setText("Campo Obligatorio*");
        }else{
            lbcantidad.setText("");
        }
        if (t_daño.getText().isEmpty()){
           lbtipodaño.setText("Campo Obligatorio*");
        }else{
            lbtipodaño.setText("");
        }
        if (t_comentario.getText().isEmpty()) {
            lbcomentario.setText("Campo Obligatorio*");
        }else{
            lbcomentario.setText("");
        }
        
    }
    
    public void validarNumero(java.awt.event.KeyEvent evento){
         //*48-57
         if(evento.getKeyChar()>=32 && evento.getKeyChar() <=47||
                 (evento.getKeyChar()>=58 && evento.getKeyChar() <=255)){
             evento.consume();
             JOptionPane.showMessageDialog(this, "Solo Numeros, no caracteres validos");
         }
         /*if (t_cantidad.getText().length()>=10) {
             evento.consume();
             JOptionPane.showMessageDialog(this, "Limite de 10");
         }*/
     }
    
    public void habilitarBoton(){
         if(t_cantidad.getText().isEmpty() ||
                 t_daño.getText().isEmpty()||
                 t_comentario.getText().isEmpty()||
                 !lbcomentario.getText().isEmpty()
                 ){
             btnCrear.setEnabled(false);
         }
         else{
             btnCrear.setEnabled(true);
         }
     }
    
    public void RellenarCBOXMarca(String tabla, String valor, JComboBox combo){
        String sql;
        sql="SELECT * FROM "+tabla;
        Statement st;
        ResultSet rs;
        //*Pasar datos de tabla carrito a pedidos
        try{
            st=cn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                cbmarca.addItem(rs.getString(valor));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error en MYSQL");
        }
        
    }
    
  
    
    void cargar(String valor){
        String [] titulos = {"ID","Tipo","Marca","Cantidad","TipoDaño","Comentario","Fecha"};
        String [] registros = new String[8];
        
        String sqsl = "SELECT * FROM reportes WHERE CONCAT (ID,'', Tipo,'', Marca,'', Cantidad,'',TipoDaño,'',Comentario,'',FechaGENE) LIKE '%"+valor+"%'";
        
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
    
    void limpiarxd(){
        cbtipo.removeAllItems();
        cbmarca.removeAllItems();
        RellenarCBOXTipos("productos","Tipo",cbtipo);
        RellenarCBOXMarca("productos","Marca", cbmarca);
        t_cantidad.setText("");
        t_daño.setText("");
        t_comentario.setText("");
        aux.setText("");
    }
    
    void limpiar(){
        cbtipo.removeAllItems();
        cbmarca.removeAllItems();
        t_cantidad.setText("");
        t_daño.setText("");
        t_comentario.setText("");
        aux.setText("");
    }
    
    void desbloquear(){
        cbtipo.setEnabled(true);
        cbmarca.setEnabled(true);
        t_cantidad.setEnabled(true);
        t_daño.setEnabled(true);
        t_comentario.setEnabled(true);
        
        btnNuevo.setEnabled(false);
        btnCrear.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
     }

    void bloquear(){
        cbtipo.setEnabled(false);
        cbmarca.setEnabled(false);
        t_cantidad.setEnabled(false);
        t_daño.setEnabled(false);
        t_comentario.setEnabled(false);
        
        btnNuevo.setEnabled(true);
        btnCrear.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_reportes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        aux = new javax.swing.JTextField();
        btnMostrarTodo = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        JPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        t_comentario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        t_cantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        cbtipo = new javax.swing.JComboBox<>();
        cbmarca = new javax.swing.JComboBox<>();
        lbtipo = new javax.swing.JLabel();
        lbmarca = new javax.swing.JLabel();
        lbcantidad = new javax.swing.JLabel();
        lbtipodaño = new javax.swing.JLabel();
        lbcomentario = new javax.swing.JLabel();
        t_daño = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        t_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(t_datos);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");

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
        jScrollPane2.setViewportView(tb_reportes);

        jLabel8.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(82, 208, 83));
        jLabel8.setText("Buscar");

        aux.setText("Buscar En La Tabla");
        aux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auxActionPerformed(evt);
            }
        });
        aux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                auxKeyReleased(evt);
            }
        });

        btnMostrarTodo.setBackground(new java.awt.Color(102, 102, 255));
        btnMostrarTodo.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnMostrarTodo.setForeground(new java.awt.Color(255, 255, 255));
        btnMostrarTodo.setText("Mostrar Todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(102, 102, 255));
        btnBorrar.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/1668640134791-removebg-preview.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(102, 102, 255));
        btnEditar.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/editar-removebg-preview.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        JPanel.setBackground(java.awt.Color.gray);
        JPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(82, 208, 83));
        jLabel6.setText("Comentario");

        t_comentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_comentarioActionPerformed(evt);
            }
        });
        t_comentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_comentarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_comentarioKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(82, 208, 83));
        jLabel2.setText("Tipo");

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(82, 208, 83));
        jLabel3.setText("Marca");

        t_cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cantidadActionPerformed(evt);
            }
        });
        t_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_cantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_cantidadKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(82, 208, 83));
        jLabel4.setText("Cantidad");

        jLabel5.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(82, 208, 83));
        jLabel5.setText("Tipo de daño");

        btnCrear.setBackground(new java.awt.Color(102, 102, 255));
        btnCrear.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/listo-removebg-preview.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(102, 102, 255));
        btnActualizar.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/cargar-removebg-preview.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(102, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/eliminar1-removebg-preview.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

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

        btnNuevo.setBackground(new java.awt.Color(102, 102, 255));
        btnNuevo.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/1668640064672-removebg-preview.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        t_daño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_dañoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_dañoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(58, 58, 58)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmarca, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_comentario, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                            .addComponent(t_daño))))
                .addGap(26, 26, 26)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbtipo, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(lbmarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbtipodaño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbcomentario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCrear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir)
                .addGap(57, 57, 57))
        );
        JPanelLayout.setVerticalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtipo))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbmarca))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(t_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcantidad))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbtipodaño)
                    .addComponent(t_daño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_comentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(lbcomentario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnNuevo)
                    .addComponent(btnActualizar))
                .addGap(22, 22, 22))
        );

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Reporte NERV");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(250, 250, 250))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(btnBorrar)
                                .addGap(39, 39, 39)
                                .addComponent(btnEditar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(aux, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMostrarTodo)))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(aux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btnEditar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void t_comentarioActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        t_comentario.transferFocus();
    }                                            

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        bloquear();
        
        String Tipo, Marca, Cantidad, Daño, Comentario;
        Tipo = cbtipo.getSelectedItem().toString();
        Marca=cbmarca.getSelectedItem().toString();
        Cantidad = t_cantidad.getText();
        Daño = t_daño.getText();
        Comentario = t_comentario. getText();
        String sql="";
        sql= "INSERT INTO reportes (ID,Tipo,Marca,Cantidad,TipoDaño,Comentario,FechaGENE) VALUES (NULL,?,?,?,?,?,current_timestamp())";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, Tipo);
            psd.setString(2, Marca);
            psd.setString(3, Cantidad);
            psd.setString(4, Daño);
            psd.setString(5, Comentario);
            
            int n = psd.executeUpdate();
            if (n>0){
                JOptionPane.showMessageDialog(null, "Registro Guardado Con Exito");
                cargar("");
            }
            
        } catch (SQLException ex) {
            //*Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, caracter repetido en base de datos intente de nuevo"+ex);
            desbloquear();
        }
        
        
    }                                        

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        MenuPrincipal home= new MenuPrincipal();
        home.setVisible(true);
        dispose();
        /*
        bloquear();
        BIENVENIDAA home1 = new BIENVENIDAA();
        home1.setVisible(true);
        dispose();
        */
    }                                           

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        MenuPrincipal home= new MenuPrincipal();
        home.setVisible(true);
        dispose();
    }                                        

    private void t_cantidadActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        t_cantidad.transferFocus();
    }                                          

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        limpiar();
        desbloquear();
        RellenarCBOXTipos("productos","Tipo",cbtipo);
        RellenarCBOXMarca("productos","Marca", cbmarca);
    }                                        

    private void auxActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
        cargar("");
        aux.setText("");
    }                                              

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        int fila=tb_reportes.getSelectedRow();
        String valor= (String) tb_reportes.getValueAt(fila, 0);
        if (fila>=0) {
            try {
                PreparedStatement psd = cn.prepareStatement("DELETE FROM reportes WHERE ID='"+valor+"'");
                psd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado con exito");
                cargar("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eleminar la tabla");
            }
        }
    }                                         

    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        desbloquear();
        int fila= tb_reportes.getSelectedRow();
        if (fila>=0){
            
            ID = tb_reportes.getValueAt(fila, 0).toString();
            cbtipo.addItem(tb_reportes.getValueAt(fila, 1).toString());
            cbmarca.addItem(tb_reportes.getValueAt(fila, 2).toString());
            t_cantidad.setText(tb_reportes.getValueAt(fila, 3).toString());
            t_daño.setText(tb_reportes.getValueAt(fila, 4).toString());
            t_comentario.setText(tb_reportes.getValueAt(fila, 5).toString());
            TIMESTAMP=tb_reportes.getValueAt(fila, 6).toString();
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    }                                         

    private void auxKeyReleased(java.awt.event.KeyEvent evt) {                                
        // TODO add your handling code here:
        cargar(aux.getText());
    }                               

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        //*PreparedStatement std = cn.prepareStatement("UPDATE clientestienda SET (IDTienda,NombreTienda,Direccion,Telefono,Correo,RFC,NombreCargo) VALUES (NULL,?,?,?,?,?,?)");
        //*UPDATE `pro"+Cantidad+"',`CostoTOT`='"+CostoTOT+"',`CostoXPZ`='"+CostoXPZ+"' WHERE `ID`='"+ID+"'"
         bloquear();
        
        String Tipo, Marca, Cantidad, Daño, Comentario;
        Tipo = cbtipo.getSelectedItem().toString();
        Marca=cbmarca.getSelectedItem().toString();
        Cantidad = t_cantidad.getText();
        Daño = t_daño.getText();
        Comentario = t_comentario. getText();
        
        
        String sql="";
        sql= "UPDATE `reportes` SET `Tipo`='"+Tipo+"',`Marca`='"+Marca+"',`Cantidad`='"+Cantidad+"',`TipoDaño`='"+Daño+"',`Comentario`='"+Comentario+"' WHERE `ID`='"+ID+"'";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cambio Actualizado");
            cargar("");
            limpiarxd();
            limpiar();
        } catch (SQLException ex) {
            //*Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, caracter repetido en base de datos intente de nuevo "+ex);
            desbloquear();
        }
    }                                             

    private void t_cantidadKeyReleased(java.awt.event.KeyEvent evt) {                                       
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                      

    private void t_cantidadKeyTyped(java.awt.event.KeyEvent evt) {                                    
        // TODO add your handling code here:
        validarNumero(evt);
    }                                   

    private void t_dañoKeyReleased(java.awt.event.KeyEvent evt) {                                   
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                  

    private void t_dañoKeyTyped(java.awt.event.KeyEvent evt) {                                
        // TODO add your handling code here:
        validacionCaracteres(evt);
    }                               

    private void t_comentarioKeyReleased(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                        

    private void t_comentarioKeyTyped(java.awt.event.KeyEvent evt) {                                      
        // TODO add your handling code here:
        validacionCaracteres(evt);
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
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel JPanel;
    private javax.swing.JTextField aux;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbmarca;
    private javax.swing.JComboBox<String> cbtipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbcantidad;
    private javax.swing.JLabel lbcomentario;
    private javax.swing.JLabel lbmarca;
    private javax.swing.JLabel lbtipo;
    private javax.swing.JLabel lbtipodaño;
    private javax.swing.JTextField t_cantidad;
    private javax.swing.JTextField t_comentario;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField t_daño;
    private javax.swing.JTable tb_reportes;
    // End of variables declaration                   
}
