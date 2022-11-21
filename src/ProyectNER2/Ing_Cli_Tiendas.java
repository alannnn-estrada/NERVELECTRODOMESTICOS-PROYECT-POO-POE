/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ProyectNER2;


import java.awt.Color;
import java.sql.*;
//*import java.util.logging.Level;
//*import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
public class Ing_Cli_Tiendas extends javax.swing.JFrame {

    /**
     * Creates new form MAIN
     */
    
    String ID;
    conectar cc = new conectar();
    Connection cn =cc.conexion();
    DefaultTableModel model;
    public Ing_Cli_Tiendas() {
        initComponents();
        this.getContentPane().setBackground(Color.gray);
        /*DefaultTableModel model;
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMaximum(30);
        nm.setMinimum(15);
        t_edad.setModel(nm);*/
        
        limpiar();
        desbloquear();
        cargar("");
        setLocationRelativeTo(null); //* Para todas las ventanas
        setIconImage(new ImageIcon(getClass().getResource("/ProyectNER2/icon/IconWindow.png")).getImage()); //*Icono del programa 
        
    }
    
    void cargar(String valor){
        String [] titulos = {"IDTienda","NomCliente","Direccion","Telefono","Correo","RFC","NombreCargo"};
        String [] registros = new String[7];
        
        String sqsl = "SELECT * FROM clientestienda WHERE CONCAT (IDTienda,'', NomCliente,'', Direccion,'',Telefono,'',Correo,'',RFC,'',NombreCargo) LIKE '%"+valor+"%'";
        
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
    
    void limpiar(){
        t_nomTienda.setText("");
        t_direccion.setText("");
        t_numeroTEL.setText("");
        t_correo.setText("");
        t_RFC.setText("");
        t_nomEmpleado.setText("");
        aux.setText("");
        validarCamposVacios();
    }
    
    void desbloquear(){
         t_nomTienda.setEnabled(true);
        t_direccion.setEnabled(true);
        t_numeroTEL.setEnabled(true);
        t_correo.setEnabled(true);
        t_RFC.setEnabled(true);
        t_nomEmpleado.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnCrear.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
     }

    void bloquear(){
        t_nomTienda.setEnabled(false);
        t_direccion.setEnabled(false);
        t_numeroTEL.setEnabled(false);
        t_correo.setEnabled(false);
        t_RFC.setEnabled(false);
        t_nomEmpleado.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnCrear.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
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
        if (t_nomTienda.getText().isEmpty()) {
            TiendaLB.setText("Campo Obligatorio*");
        }else{
            TiendaLB.setText("");
        }
        if (t_direccion.getText().isEmpty()) {
            DireLB.setText("Campo Obligatorio*");
        }else{
            DireLB.setText("");
        }
        if (t_numeroTEL.getText().isEmpty()){
           NumLB.setText("Campo Obligatorio*");
        }else{
            NumLB.setText("");
        }
        if (t_correo.getText().isEmpty()) {
            CorreoLB.setText("Campo Obligatorio*");
        }else if(!t_correo.getText().contains("@") || !t_correo.getText().contains(".") ){
            CorreoLB.setText("Correo no Valido");
        }else{
            CorreoLB.setText("");
        }
        if (t_RFC.getText().isEmpty()) {
            RFCLB.setText("Campo Obligatorio*");
        }else{
            RFCLB.setText("");
        }
        if (t_nomEmpleado.getText().isEmpty()) {
            EmpleLB.setText("Campo Obligatorio*");
        }else{
            EmpleLB.setText("");
        }
    }
    
   
    
     public void validarCorreo(java.awt.event.KeyEvent evento){
         if(evento.getKeyChar() >=32 && evento.getKeyChar()<=44 || 
                 (evento.getKeyChar()==47) || 
                 (evento.getKeyChar() >=58 && evento.getKeyChar() <=63) ||
                 (evento.getKeyChar() >=91 && evento.getKeyChar() <=94) ||
                 (evento.getKeyChar() ==96)||
                 (evento.getKeyChar() >=123 && evento.getKeyChar() <=255)
                 ){
             evento.consume();
             JOptionPane.showMessageDialog(this, "No se permite ese caracter en especifico");
         }
     }
     
     public void validarNumero(java.awt.event.KeyEvent evento){
         //*48-57
         if(evento.getKeyChar()>=32 && evento.getKeyChar() <=47||
                 (evento.getKeyChar()>=58 && evento.getKeyChar() <=255)){
             evento.consume();
             JOptionPane.showMessageDialog(this, "Solo Numeros, no caracteres validos");
         }
         
     }
     
     public void validarRFC(java.awt.event.KeyEvent evento){
         //*48-57
         //*65-90
         
         if(evento.getKeyChar()>=32 && evento.getKeyChar()<=47||
                 (evento.getKeyChar()>=58 && evento.getKeyChar() <=64)||
                 (evento.getKeyChar()>=91 && evento.getKeyChar() <=255)){
             evento.consume();
             JOptionPane.showMessageDialog(this, "Solo MAYUSCULAS y caracteres validos");
         }
         
     }
     
     public void validarDireccion(java.awt.event.KeyEvent evento){
         if(evento.getKeyChar()>=32 && evento.getKeyChar()<=47 ||
                 (evento.getKeyChar()>=58 && evento.getKeyChar()<=64)||
                 (evento.getKeyChar()>=91 && evento.getKeyChar()<=96)||
                 (evento.getKeyChar()>=123 && evento.getKeyChar()<=255)){
             
         }
     }
     
     public void habilitarBoton(){
         if(t_nomTienda.getText().isEmpty() ||
                 t_direccion.getText().isEmpty()||
                 t_numeroTEL.getText().isEmpty()||
                 t_correo.getText().isEmpty()||
                 !CorreoLB.getText().isEmpty()||
                 t_RFC.getText().isEmpty()||
                 t_nomEmpleado.getText().isEmpty()
                 ){
             btnCrear.setEnabled(false);
         }
         else{
             btnCrear.setEnabled(true);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_datosCliTiendas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        aux = new javax.swing.JTextField();
        btnMostrarTodo = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        JPanel = new javax.swing.JPanel();
        t_nomEmpleado = new javax.swing.JTextField();
        t_nomTienda = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        t_RFC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        t_direccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        t_numeroTEL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_correo = new javax.swing.JTextField();
        TiendaLB = new javax.swing.JLabel();
        NumLB = new javax.swing.JLabel();
        CorreoLB = new javax.swing.JLabel();
        DireLB = new javax.swing.JLabel();
        RFCLB = new javax.swing.JLabel();
        EmpleLB = new javax.swing.JLabel();

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
        setTitle("Crear Cuenta Tiendas");
        setIconImages(null);

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

        btnCancelar.setBackground(new java.awt.Color(102, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Book Antiqua", 3, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectNER2/Iconos/1233.png"))); // NOI18N
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
        jScrollPane2.setViewportView(tb_datosCliTiendas);

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

        JPanel.setBackground(java.awt.Color.gray);
        JPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        t_nomEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nomEmpleadoActionPerformed(evt);
            }
        });
        t_nomEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_nomEmpleadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_nomEmpleadoKeyTyped(evt);
            }
        });

        t_nomTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_nomTiendaActionPerformed(evt);
            }
        });
        t_nomTienda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_nomTiendaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_nomTiendaKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(82, 208, 83));
        jLabel6.setText("RFC");

        t_RFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_RFCActionPerformed(evt);
            }
        });
        t_RFC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_RFCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_RFCKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(82, 208, 83));
        jLabel7.setText("Nombre Del Empleado A Cargo");

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(82, 208, 83));
        jLabel1.setText("Ingreso Tiendas NERV");

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(82, 208, 83));
        jLabel2.setText("Nombre De La Tienda");

        t_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_direccionActionPerformed(evt);
            }
        });
        t_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_direccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_direccionKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(82, 208, 83));
        jLabel3.setText("Direccion");

        t_numeroTEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_numeroTELActionPerformed(evt);
            }
        });
        t_numeroTEL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_numeroTELKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_numeroTELKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(82, 208, 83));
        jLabel4.setText("Numero Telefonico");

        jLabel5.setFont(new java.awt.Font("Book Antiqua", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(82, 208, 83));
        jLabel5.setText("Correo de la tienda");

        t_correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_correoActionPerformed(evt);
            }
        });
        t_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_correoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_correoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7))
                            .addGroup(JPanelLayout.createSequentialGroup()
                                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addGap(60, 60, 60)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(t_RFC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_numeroTEL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_nomTienda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(t_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t_nomEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)))
                .addGap(35, 35, 35)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TiendaLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorreoLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DireLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RFCLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EmpleLB, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        JPanelLayout.setVerticalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_nomTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(TiendaLB))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(t_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DireLB))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(t_numeroTEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumLB))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(t_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorreoLB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(t_RFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RFCLB))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_nomEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(EmpleLB))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                                .addComponent(btnMostrarTodo)))
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir)
                        .addGap(118, 118, 118))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrear)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalir)
                    .addComponent(btnNuevo)
                    .addComponent(btnActualizar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(aux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>                        

    private void t_RFCActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
        t_RFC.transferFocus();
    }                                     

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if(t_numeroTEL.getText().length()==10 && t_RFC.getText().length()==12){
            bloquear();
        
            String NombreTienda, Direccion, NumeroTEl, Correo, RFC, NombreEmpleado;
            NombreTienda = t_nomTienda.getText();
            Direccion = t_direccion.getText();
            NumeroTEl = t_numeroTEL.getText();
            Correo = t_correo.getText();
            RFC = t_RFC. getText();
            NombreEmpleado = t_nomEmpleado.getText();

            String sql="";
            sql= "INSERT INTO clientestienda (IDTienda,NomCliente,Direccion,Telefono,Correo,RFC,NombreCargo) VALUES (NULL,?,?,?,?,?,?)";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, NombreTienda);
                psd.setString(2, Direccion);
                psd.setString(3, NumeroTEl);
                psd.setString(4, Correo);
                psd.setString(5, RFC);
                psd.setString(6, NombreEmpleado);
                int n = psd.executeUpdate();
                if (n>0){
                    JOptionPane.showMessageDialog(null, "Registro Guardado Con Exito");
                    cargar("");
                }

            } catch (SQLException ex) {
                //*Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, caracter repetido en base de datos intente de nuevo");
                desbloquear();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Error en el numero telefonico o RFC");
            
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

    private void t_direccionActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        t_direccion.transferFocus();
    }                                           

    private void t_numeroTELActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        t_numeroTEL.transferFocus();
    }                                           

    private void t_correoActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        t_correo.transferFocus();
    }                                        

    private void t_nomEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        t_nomEmpleado.transferFocus();
    }                                             

    private void t_nomTiendaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        t_nomTienda.transferFocus();
    }                                           

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        limpiar();
        desbloquear();
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
        int fila=tb_datosCliTiendas.getSelectedRow();
        String valor= (String) tb_datosCliTiendas.getValueAt(fila, 0);
        if (fila>=0) {
            try {
                PreparedStatement psd = cn.prepareStatement("DELETE FROM clientestienda WHERE IDTienda='"+valor+"'");
                psd.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado con exito");
                cargar("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eleminar de la tabla");
            }
        }
    }                                         

    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        desbloquear();
        int fila= tb_datosCliTiendas.getSelectedRow();
        if (fila>=0){
            
            ID = tb_datosCliTiendas.getValueAt(fila, 0).toString();
            t_nomTienda.setText(tb_datosCliTiendas.getValueAt(fila, 1).toString());
            t_direccion.setText(tb_datosCliTiendas.getValueAt(fila, 2).toString());
            t_numeroTEL.setText(tb_datosCliTiendas.getValueAt(fila, 3).toString());
            t_correo.setText(tb_datosCliTiendas.getValueAt(fila, 4).toString());
            t_RFC.setText(tb_datosCliTiendas.getValueAt(fila, 5).toString());
            t_nomEmpleado.setText(tb_datosCliTiendas.getValueAt(fila, 6).toString());
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
        
        String NombreTienda, Direccion, NumeroTEl, Correo, RFC, NombreEmpleado;
        NombreTienda = t_nomTienda.getText();
        Direccion = t_direccion.getText();
        NumeroTEl = t_numeroTEL.getText();
        Correo = t_correo.getText();
        RFC = t_RFC. getText();
        NombreEmpleado = t_nomEmpleado.getText();
        
        try {
            PreparedStatement psd = cn.prepareStatement("UPDATE `clientestienda` SET `NomCliente`='"+NombreTienda+"',`Direccion`='"+Direccion+"',`Telefono`='"+NumeroTEl+"',`Correo`='"+Correo+"',`RFC`='"+RFC+"',`NombreCargo`='"+NombreEmpleado+"' WHERE `IDTienda`='"+ID+"'");
            psd.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cambio Actualizado");
            cargar("");
            limpiar();
        } catch (SQLException ex) {
            //*Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, caracter repetido en base de datos intente de nuevo");
            desbloquear();
        }
    }                                             

    private void t_nomTiendaKeyReleased(java.awt.event.KeyEvent evt) {                                        
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                       

    private void t_nomTiendaKeyTyped(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
        validacionCaracteres(evt);
    }                                    

    private void t_direccionKeyReleased(java.awt.event.KeyEvent evt) {                                        
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                       

    private void t_direccionKeyTyped(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
        validarDireccion(evt);
    }                                    

    private void t_nomEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {                                          
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                         

    private void t_nomEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {                                       
        // TODO add your handling code here:
        validacionCaracteres(evt);
    }                                      

    private void t_correoKeyReleased(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                    

    private void t_correoKeyTyped(java.awt.event.KeyEvent evt) {                                  
        // TODO add your handling code here:
        validarCorreo(evt);
    }                                 

    private void t_numeroTELKeyReleased(java.awt.event.KeyEvent evt) {                                        
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                       

    private void t_numeroTELKeyTyped(java.awt.event.KeyEvent evt) {                                     
        // TODO add your handling code here:
        validarNumero(evt);
    }                                    

    private void t_RFCKeyReleased(java.awt.event.KeyEvent evt) {                                  
        // TODO add your handling code here:
        habilitarBoton();
        validarCamposVacios();
    }                                 

    private void t_RFCKeyTyped(java.awt.event.KeyEvent evt) {                               
        // TODO add your handling code here:
        validarRFC(evt);
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
            java.util.logging.Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ing_Cli_Tiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Ing_Cli_Tiendas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel CorreoLB;
    private javax.swing.JLabel DireLB;
    private javax.swing.JLabel EmpleLB;
    private javax.swing.JPanel JPanel;
    private javax.swing.JLabel NumLB;
    private javax.swing.JLabel RFCLB;
    private javax.swing.JLabel TiendaLB;
    private javax.swing.JTextField aux;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField t_RFC;
    private javax.swing.JTextField t_correo;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField t_direccion;
    private javax.swing.JTextField t_nomEmpleado;
    private javax.swing.JTextField t_nomTienda;
    private javax.swing.JTextField t_numeroTEL;
    private javax.swing.JTable tb_datosCliTiendas;
    // End of variables declaration                   
}
