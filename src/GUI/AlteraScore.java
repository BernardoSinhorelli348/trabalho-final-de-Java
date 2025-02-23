/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import Classes.Score;
import DAO.ScoreDAO;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AlteraScore extends javax.swing.JFrame {
    public AlteraScore() {
      initComponents();
        setResizable(false); //sem tela cheia
        // Adiciona o evento de clique ao botão
        jToggleButton1.addActionListener(evt -> alterarScore());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        BC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton1.setText("Clique aqui para alterar o score");

        BC.setText("Cancelar");
        BC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jToggleButton1)
                .addGap(80, 80, 80)
                .addComponent(BC)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCActionPerformed
        dispose();
    }//GEN-LAST:event_BCActionPerformed
private void alterarScore() {
    ScoreDAO scoreDAO = new ScoreDAO();
    ArrayList<Score> scores = scoreDAO.listarTodos();
    String msg= "";
    for (Score s : scores) {
        msg += s.getId_score() + "|";
    }
    String idsMsg = msg;
    String inputId = JOptionPane.showInputDialog(this, 
            "Digite o ID do score que deseja alterar:\nIDs Disponíveis:\n" + idsMsg);
    if (inputId == null || inputId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID não informado.");
        return;
    }
    
    int id;
    try {
        id = Integer.parseInt(inputId);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "ID inválido!");
        return;
    }
    Score score = scoreDAO.procurar2(id);
    if (score == null) {
        JOptionPane.showMessageDialog(this, "Score não encontrado para o ID: " + id);
        return;
    }
    String novoScoreStr = JOptionPane.showInputDialog(this, 
            "Score Atual: " + score.getScore() + "\nDigite o novo score:", score.getScore());
    if (novoScoreStr == null || novoScoreStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Novo score não informado.");
        return;
    }
    
    int novoScore;
    try {
        novoScore = Integer.parseInt(novoScoreStr);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Novo score inválido!");
        return;
    }
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
    String dataAtualFormatada = (score.getData() != null) ? sdf.format(score.getData()) : "";
    String novaDataStr = JOptionPane.showInputDialog(this, 
            "Data Atual: " + dataAtualFormatada + "\nDigite a nova data (dd/MM/yyyy):", dataAtualFormatada);
    if (novaDataStr == null || novaDataStr.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Nova data não informada.");
        return;
    }
    
    java.util.Date novaData;
    try {
        novaData = sdf.parse(novaDataStr);
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(this, "Data inválida. Por favor, utilize o formato dd/MM/yyyy.");
        return;
    }
    score.setScore(novoScore);
    score.setData(new java.sql.Date(novaData.getTime()));
    scoreDAO.atualizar2(score);
    JOptionPane.showMessageDialog(this, "Score e data alterados com sucesso!");
    Score scoreAtualizado = scoreDAO.procurar2(id);
    System.out.println("Score após atualização: " + scoreAtualizado);
}


    

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BC;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}