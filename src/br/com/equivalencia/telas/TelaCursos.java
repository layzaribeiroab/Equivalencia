
package br.com.equivalencia.telas;
import br.com.equivalencia.dao.ModuloConexao;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;



public class TelaCursos extends javax.swing.JFrame {

  Connection conexao = null;
  PreparedStatement pst = null;
  ResultSet rs = null;
  
  //criando o metodo adcionar 
  private void adicionar (){
    String sql = "Insert into tb_area_tecnologica(nome_area) Values(?)";
    
    try {
     pst = conexao.prepareStatement(sql);
     pst.setString(1, txtNomeCurso.getText());
     
  // validando campos de preencimento obrigatorio
  
            if (txtNomeCurso.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "Campos de preenchimento obrigatorio estao em branco!");
            }else{
                int adicionado = pst.executeUpdate();
                
                if (adicionado >0) {
                    JOptionPane.showMessageDialog(null, "Área Tecnologica cadastrada com sucesso");
                    txtNomeCurso.setText(null);
                    txtNomeCurso.requestFocus();
                }
            }
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
} 
  
  // metodo para alterar um registro no banco de dados  
  private void alterar(){
      String sql = "update tb_area_tecnologica set nome_area =?  where id_area =?";
      
      try {
          pst=conexao.prepareStatement(sql);
          pst.setString(1, txtNomeCurso.getText());
          pst.setString(2, txtIdCurso.getText());
       
          //Validacao dos campos obrigatorios
          if (txtIdCurso.getText().isEmpty() || (txtNomeCurso.getText().isEmpty())) {
              JOptionPane.showMessageDialog(null, "Campos de preenchimento obrigatório em branco");
              
          } else {
              int adicionado = pst.executeUpdate();
              
              if (adicionado >0){
                  JOptionPane.showMessageDialog(null, "Area Tecnologica alterada com sucesso!");
                  txtNomeCurso.requestFocus();
                  txtNomeCurso.setText(null);
                  txtIdCurso.setText(null);
                  btnCadastrar.setEnabled(true);
                  btnEditar.setEnabled(false);
                  btnExcluir.setEnabled(false);
              }
          }
      } catch (Exception e) {
          
          JOptionPane.showMessageDialog(null, e);
          
      }
      
  }
  
  // metodo de pesquisa area tecnologica no banco de dados
  
  private void pesquisar_curso(){
      String sql = "select id_curso as ID, nome_curso as 'Curso', id_area as 'ID Área Téc.' from tb_cursos where nome_curso like ?";
      
      try {
          pst = conexao.prepareStatement(sql);
          pst.setString(1, txtPesquisarCurso.getText()+"%");
          rs = pst.executeQuery();
          
          tblCursos.setModel(DbUtils.resultSetToTableModel(rs));
          
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, e);
      }
  }
  
  private void setar_campos(){
      int setar = tblCursos.getSelectedRow();
      txtIdCurso.setText(tblCursos.getModel().getValueAt(setar, 0).toString());
      txtNomeCurso.setText(tblCursos.getModel().getValueAt(setar, 1).toString());
      btnCadastrar.setEnabled(false);
      btnEditar.setEnabled(true);
      btnExcluir.setEnabled(true);
  }
  
  //O método abaixo exclui um registro do banco de dados
  private void excluir(){
      int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este registro?","Atenção",JOptionPane.YES_NO_OPTION);
      if (confirma == JOptionPane.YES_OPTION){
          String sql = "delete from tb_area_tecnologica where id_area=?";
          try {
              pst = conexao.prepareStatement(sql);
              pst.setString(1, txtIdCurso.getText());
              int apagado = pst.executeUpdate();
              if (apagado > 0){
                  JOptionPane.showMessageDialog(null, "Área Tecnológica excluída com sucesso!");
                  txtIdCurso.setText(null);
                  txtNomeCurso.setText(null);
                  btnExcluir.setEnabled(false);
                  btnEditar.setEnabled(false);
                  txtNomeCurso.requestFocus();
                  btnCadastrar.setEnabled(true);
              }
          } catch (java.sql.SQLIntegrityConstraintViolationException e) {
              JOptionPane.showMessageDialog(null, "Erro ao excluir área tecnológica.\n" + 
             "Existe um curso cadastrado para esta área tecnológica!");
              System.out.println(e);
         
              
          } catch (Exception e1){
              JOptionPane.showMessageDialog(null, e1);
          }
      }
  }
  
    
    public TelaCursos() {
        initComponents();
        conexao = ModuloConexao.conector();
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        pesquisar_curso();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdCurso = new javax.swing.JTextField();
        txtNomeCurso = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtPesquisarCurso = new javax.swing.JTextField();
        cboArea = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("ID:");

        jLabel3.setText("Nome Curso:");

        txtIdCurso.setEnabled(false);

        txtNomeCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeCursoKeyPressed(evt);
            }
        });

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/btnCadastro.png"))); // NOI18N
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/delete.png"))); // NOI18N
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/equivalencia/imagens/edit.png"))); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome Área"
            }
        ));
        tblCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCursos);

        jLabel4.setText("Pesquisar:");

        txtPesquisarCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarCursoKeyReleased(evt);
            }
        });

        jLabel5.setText("ID Area Téc.:");

        jTextField1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboArea, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarCurso))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir)
                    .addComponent(btnCadastrar)
                    .addComponent(btnEditar))
                .addContainerGap(144, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtPesquisarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
      adicionar ();
      pesquisar_curso();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtNomeCursoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeCursoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            adicionar();
        }
    }//GEN-LAST:event_txtNomeCursoKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar();
        pesquisar_curso();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtPesquisarCursoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarCursoKeyReleased
        pesquisar_curso();
    }//GEN-LAST:event_txtPesquisarCursoKeyReleased

    private void tblCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCursosMouseClicked
        // TODO add your handling code here:
        setar_campos();
    }//GEN-LAST:event_tblCursosMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCursos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JComboBox<String> cboArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField txtIdCurso;
    private javax.swing.JTextField txtNomeCurso;
    private javax.swing.JTextField txtPesquisarCurso;
    // End of variables declaration//GEN-END:variables
}
