/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Agustin
 */
public class frmCambioDolar extends javax.swing.JFrame {

    public String[] ipServersError={"pruebas","pruebas","villa","villa","practicas"};
    public String[] ipServers={"172.16.8.201","172.16.8.201","172.16.8.203","172.16.8.203","172.16.8.202"};
    public String[] instancias={"tin","tinviscachani","tin","tinpla","tin"};
    
    public String suc="",bd="",pw="",usr="",usuario="",ip="",inst="";
    /**
     * Creates new form frmCambioDolar
     */
    public frmCambioDolar(String[] args) {
        initComponents();
        txtPrueba.setVisible(false);
        llenaTipoCambioDelDia();
        llenaTablaDolar();
        suc=args[3];
        bd=args[0];
        pw=args[1];
        usr=args[6];
        usuario=args[2];
        ip=args[5];
        inst=args[4];
        lblSucursal.setText(suc);
        lblBD.setText(bd);
        ConexionBD.cierraConexion();
        
    }

    public void llenaTipoCambioDelDia(){
       
        
       // String query = "SELECT current_date as FECHA_ACTUAL,COALESCE(CAMBIO,'0') AS CAMBIO\n" +
            //   "FROM rdb$database LEFT JOIN codolar ON current_date='"+A.convierte_AMD_a_MDA_guion(ConexionBD.fechaActualServidor())+"'";
         String query = "SELECT current_date AS FECHA_ACTUAL,COALESCE(CAMBIO,'0') AS CAMBIO,COALESCE(CAMUFV,'0') AS CAMUFV\n" +
                        "FROM rdb$database LEFT JOIN codolar \n" +
                        "on current_date=fecha and current_date='"+A.convierte_AMD_a_MDA_guion(ConexionBD.fechaActualServidor())+"'";
        ResultSet rs;
        
        try {
            rs=ConexionBD.querySelect(query);
            while(rs.next()){
                txtFecha.setText(A.convierte_AMD_a_DMA_barra(rs.getString("FECHA_ACTUAL")));
                txtDolar.setText(A.convDecimalPunto(rs.getString("CAMBIO")));
                txtUFV.setText(rs.getString("CAMUFV"));
            }
            rs.close();
        } catch (SQLException ex) {
            A.mensaje("Error en llenaTipoCambioDelDia: "+ex.getMessage());
        }
    }
    public void llenaTablaDolar(){
        
        try {
            if (ConexionBD.cnn.isClosed()){
                ConexionBD.conectar(ip, inst, bd, usr, pw);
            }
        } catch (SQLException ex) {
            A.mensaje("Error en reconexion llenaTablaDolar, Error: "+ex.getMessage());
        }
            
        DefaultTableModel dtm= new DefaultTableModel();
        
        //esta seccion bloquea la edición de las celdas
        Class<?> columna = tblDolar.getColumnClass(0);
        tblDolar.setDefaultEditor(columna, null);
        //////////////////////////////
        
        String[] cabecera={ "Fecha", "Tipo de cambio","Cambio UFV"};
        //DefaultTableModel dtm= new DefaultTableModel(new String[] { "Fecha", "Tipo de cambio"},0);
        dtm.setColumnIdentifiers(cabecera);
        tblDolar.setModel(dtm);
        int f=0;
        String query="SELECT B.FECHA AS FECHA,B.CAMBIO AS DOLAR,B.CAMUFV AS CAMUFV\n" +
        "FROM\n" +
        "(SELECT FIRST 10 FECHA,CAMBIO,CAMUFV FROM CODOLAR\n" +
        "ORDER BY FECHA DESC) AS B\n" +
        "ORDER BY B.FECHA ASC";
        
        ResultSet rs;
        
        
        try {
            rs=ConexionBD.querySelect(query);
            
            while(rs.next()){
                dtm.setRowCount(rs.getRow());
                //dtm.setValueAt(A.convierte_AMD_a_DMA_guion(rs.getString("FECHA")), f, 0);
                dtm.setValueAt(A.convierte_AMD_a_DMA_barra(rs.getString("FECHA")), f, 0);
                dtm.setValueAt(rs.getString("DOLAR"), f, 1);
                dtm.setValueAt(rs.getString("CAMUFV"), f, 2);
                f++;
            }
            rs.close();
            ConexionBD.cierraConexion();
        } catch (SQLException ex) {
            A.mensaje("Error en llenaTablaDolar: "+ex.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDolar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDolar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblSucursal = new javax.swing.JLabel();
        cmbGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaLog = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblBD = new javax.swing.JLabel();
        txtPrueba = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtUFV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tipo de cambio dolar");

        tblDolar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDolar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDolarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDolar);

        jLabel1.setText("Fecha seleccionada:");

        txtFecha.setEditable(false);

        jLabel2.setText("Tipo de cambio dolar:");

        txtDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDolarActionPerformed(evt);
            }
        });
        txtDolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDolarKeyTyped(evt);
            }
        });

        jLabel3.setText("Sucursal:");

        cmbGuardar.setText("GUARDAR CAMBIOS");
        cmbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGuardarActionPerformed(evt);
            }
        });

        txaLog.setColumns(20);
        txaLog.setRows(5);
        jScrollPane2.setViewportView(txaLog);

        jLabel4.setText("Registro del proceso:");

        jLabel5.setText("Gestión:");

        txtPrueba.setText("pruebas conexiones");
        txtPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPruebaActionPerformed(evt);
            }
        });

        jLabel6.setText("Tipo de cambio UFV:");

        txtUFV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUFVActionPerformed(evt);
            }
        });
        txtUFV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUFVKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUFV, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(cmbGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUFV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGuardar)
                    .addComponent(txtPrueba))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDolarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDolarMouseClicked
        
        
        txtFecha.setText((String) tblDolar.getValueAt(tblDolar.getSelectedRow(), 0));
        txtDolar.setText((String) tblDolar.getValueAt(tblDolar.getSelectedRow(), 1));
        txtUFV.setText((String) tblDolar.getValueAt(tblDolar.getSelectedRow(), 2));
        
    }//GEN-LAST:event_tblDolarMouseClicked

    private void cmbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGuardarActionPerformed
        if(txtDolar.getText().matches("") || txtDolar.getText().matches("0")){
            A.mensaje("Debe ingresar un valor mayor a 0");
            return;
        }
        //txtDolar.is
        if(A.confirma("Va a asignar el tipo de cambio dolar: "+txtDolar.getText()+" y UFV: "+txtUFV.getText()+" en Villa Fatima,Viscachani,El alto,Soplado EAL,Llojeta,desea continuar?")==0){
        
        String acumula="",acumula2="";
        //revisa conexion en cada sucursal
        for(int i=0;i<5;i++){
            if(revisaConexiones(ipServers[i],instancias[i])){
                acumula=acumula+"Se establecio conexion con: "+ipServersError[i]+"-"+instancias[i]+"\n";
                txaLog.setText(acumula);
            }else{
                acumula=acumula+"NO SE PUDO ESTABLECER CONEXION CON: "+ipServersError[i]+"-"+instancias[i]+"\n";
                txaLog.setText(acumula);
                ipServers[i]="0";
                
            }
        }    
              //revisa conexion y dolar en cada sucursal
        acumula2=acumula;
        for(int j=0;j<5;j++){
          if(!ipServers[j].matches("0")){
            if(revisaDatos(ipServers[j],instancias[j])){
                //inserta el tipo de cambio
                
                
                ConexionBD.conectar(ipServers[j], instancias[j], bd, usr, pw);
                String query="INSERT INTO CODOLAR (FECHA,CAMBIO,CAMUFV,USERCREA,FECHACREA)\n" +
                        "VALUES ('"+A.convierte_DMA_a_MDA_barra(txtFecha.getText())+"',\n" +
                        "'"+txtDolar.getText()+"',\n" +
                        "'"+txtUFV.getText()+"',\n" +
                        "'"+usuario+"',\n" +
                        "'"+A.convierte_AMD_a_MDA_guion(ConexionBD.fechaActualServidor())+"')";
                
                //Actualiza en COUSURAIO el campo fechaautoriza para todos los usuarios
                
                String fechaActualiza="";
                
                //solo actualiza fechaautoriza si la fecha es la del día actual y no anteriores
                int flag=0;
                if(A.convierte_AMD_a_DMA_barra(ConexionBD.fechaActualServidor()).matches(txtFecha.getText()))
                {
                    if(A.obtieneDiaSemana(txtFecha.getText())==2){
                        fechaActualiza=A.fechaSumaResta(txtFecha.getText(), -2);
                    }else{
                        fechaActualiza=A.fechaSumaResta(txtFecha.getText(), -1);
                    }
                    flag=1;
                    
                }
               
                try {
                    ConexionBD.queryInsertUpdateDelete(query);
                        //solo actuliza COUSUARIO si la fecha del dia seleccionada es igual a la fecha actual flag=1
                        if (flag==1){
                           String query2="UPDATE COUSUARIO SET FECHAAUTORIZA='"+A.convierte_DMA_a_MDA_barra(fechaActualiza)+"'";
                           ConexionBD.conectar2(ipServers[j], instancias[j], "sistemas.fdb", usr, pw);
                           ConexionBD.queryInsertUpdateDelete2(query2); 
                           ConexionBD.cierraConexion2();
                        }
                    
                    acumula2=acumula2+"Dato Tipo de Cambio dolar y UFV ingresado a: "+ipServersError[j]+"-"+instancias[j]+"\n";
                    ConexionBD.cierraConexion();
                    
                } catch (SQLException ex) {
                   A.mensaje("Ocurrio un error al intentar ingresar el tipo de cambio a: "+ipServersError[j]+"-"+instancias[j]+" Error: "+ex.getMessage());
                   ConexionBD.cierraConexion();
                }
            }else{
                //actualiza el tipo de cambio
                 ConexionBD.conectar(ipServers[j], instancias[j], bd, usr, pw);
                String query="UPDATE CODOLAR SET CAMBIO='"+txtDolar.getText()+"',\n" +
                        "CAMUFV='"+txtUFV.getText()+"',\n" +
                        "USERMOD='"+usuario+"',\n" +
                        "FECHAMOD='"+A.convierte_AMD_a_MDA_guion(ConexionBD.fechaActualServidor())+"'\n" +
                        "WHERE FECHA='"+A.convierte_DMA_a_MDA_barra(txtFecha.getText())+"'";
                       
                try {
                    ConexionBD.queryInsertUpdateDelete(query);
                    acumula2=acumula2+"Dato Tipo de cambio Dolar y UFV modificado en: "+ipServersError[j]+"-"+instancias[j]+"\n";
                    ConexionBD.cierraConexion();
                } catch (SQLException ex) {
                   A.mensaje("Ocurrio un error al intentar modificar el tipo de cambio en: "+ipServersError[j]+"-"+instancias[j]+" Error: "+ex.getMessage());
                   ConexionBD.cierraConexion();
                }
                
             }
           }else
          {
              acumula2=acumula2+"NO SE ASIGNO Tipo de cambio Dolar en: "+ipServersError[j]+"-"+instancias[j]+"\n";
          }
          
        }//fin FOR
        txaLog.setText(acumula2);
        A.mensaje("El proceso ha finalizado, revise el Registro del proceso para verificar los cambios realizados");
        llenaTablaDolar();
        
      }//fin pregunta
    }//GEN-LAST:event_cmbGuardarActionPerformed

    private void txtDolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolarKeyTyped
        //evt.getKeyChar() devuelve el caracter que se haya presionado en el TextField
        if (!A.esNumeroConPunto(evt.getKeyChar())){
            getToolkit().beep(); //despliega la pantalla con beep
            evt.consume();//borra en el TextField solo el caracter que no es valido
            A.mensaje("Solo se permiten valores numericos");
        }
       /* char c=evt.getKeyChar();
        
        if(!Character.isDigit(c) && c!='.' && c!='\b'){
             getToolkit().beep(); //despliega la pantalla con beep
            evt.consume();//borra en el TextField solo el caracter que no es valido
            A.mensaje("Solo se permiten valores numericos");
        }*/
    }//GEN-LAST:event_txtDolarKeyTyped

    private void txtDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDolarActionPerformed

    private void txtPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPruebaActionPerformed
      
        
        A.mensaje(A.fechaSumaResta("21/10/2015", -30));
           //A.mensaje(Integer.toString(A.obtieneDiaSemana("17/10/2015")));
           
         /*   String dt = "2015-02-28";  // Start date
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
            c.add(Calendar.DATE, 1);  // number of days to add
            dt = sdf.format(c.getTime());  // dt is now the new date
            A.mensaje(dt);
        } catch (ParseException ex) {
            Logger.getLogger(frmCambioDolar.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
            /*GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(d);
            return cal.get(Calendar.DAY_OF_WEEK);*/
            /*        ConexionBD.conectar(ip, inst, bd, usr, pw);
            ResultSet rs,rs2;
            //String query="SELECT N_ID FROM P_PRUEBASJAVA('juju','666')";
            String query="SELECT N_ID FROM TJAVA";
            ConexionBD.conectar2(ip, inst, bd, usr, pw);
            rs=ConexionBD.querySelect(query);
            try {
            while(rs.next()){
            
            //String query2="INSERT INTO TJAVADET (N_ID,C_DESC) VALUES ('"+rs.getString("N_ID")+"','detalle 4')";
            //ConexionBD.queryInsertUpdateDelete(query2);
            String query2="SELECT C_DESC FROM TJAVADET WHERE N_ID='"+rs.getString("N_ID")+"'";
            rs2=ConexionBD.querySelect2(query2);
            if(rs2.next()){
            A.mensaje("datos: "+rs2.getString("C_DESC"));
            //A.mensaje("datos: "+rs.getString("N_ID"));
            //A.mensaje(query2);
            }else
            {
            A.mensaje("sin datos");
            }
            //A.mensaje("Se inserto correctamente");
            //A.mensaje("final");
            ConexionBD.cierraConexion2();
            ConexionBD.cierraConexion();
            }
            
            } catch (SQLException ex) {
            A.mensaje("Error capturado: "+ex.getMessage());
            }*/
       
       
        
    }//GEN-LAST:event_txtPruebaActionPerformed

    private void txtUFVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUFVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUFVActionPerformed

    private void txtUFVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUFVKeyTyped
        if (!A.esNumeroConPunto(evt.getKeyChar())){
            getToolkit().beep(); //despliega la pantalla con beep
            evt.consume();//borra en el TextField solo el caracter que no es valido
            A.mensaje("Solo se permiten valores numericos");
        }
    }//GEN-LAST:event_txtUFVKeyTyped

    public boolean revisaConexiones(String serv,String inst){
        ConexionBD.conectar(serv, inst, bd, usr, pw);
        try {
            if(ConexionBD.cnn.isValid(1)){
             ConexionBD.cierraConexion();
             return true;
            }
        } catch (SQLException ex) {
            A.mensaje("Se encontro un problema al conectar a: "+serv+"-"+inst+" Error:"+ex.getMessage());
            return false;
        }
        return false;
    }
    
    public boolean revisaDatos(String serv,String inst){
        ConexionBD.conectar(serv, inst, bd, usr, pw);
        if((ConexionBD.obtieneDolar(A.convierte_DMA_a_MDA_barra(txtFecha.getText()))).matches("0")){
            ConexionBD.cierraConexion();
            return true;
        }else{
        return false;
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
            java.util.logging.Logger.getLogger(frmCambioDolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCambioDolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCambioDolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCambioDolar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCambioDolar(args).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmbGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBD;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JTable tblDolar;
    private javax.swing.JTextArea txaLog;
    private javax.swing.JTextField txtDolar;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JButton txtPrueba;
    private javax.swing.JTextField txtUFV;
    // End of variables declaration//GEN-END:variables
}
