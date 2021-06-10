package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import morador.AgendaMorador;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfBloco;

    @FXML
    private TextField tfApto;

    @FXML
    private TextField tfData;

    @FXML
    private TableView<AgendaMorador> tvAgendaPiscina;

    @FXML
    private TableColumn<AgendaMorador, String> colMorador;

    @FXML
    private TableColumn<AgendaMorador, Integer> colBloco;

    @FXML
    private TableColumn<AgendaMorador, Integer> colNumApto;

    @FXML
    private TableColumn<AgendaMorador, String> colData;

    @FXML
    private Button btnAgendar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    void hundleButtonActioon(ActionEvent event) {
        if(event.getSource() == btnAgendar){
            insertSalvar();
            JOptionPane.showMessageDialog(null, "Agendamento salvo!!");
        }else if(event.getSource() == btnAlterar){
            updateAlterar();
            JOptionPane.showMessageDialog(null, "Agendamento alterado!!");
        }else if(event.getSource() == btnExcluir){
            deleteExcluir();
            JOptionPane.showMessageDialog(null, "Agendamento excluido!!");
        }
    }

    /* @Override */
    public void initialize(URL url, ResourceBundle rb){
        mostrarAgenda();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/condominiopoo", "root", "mysql123");
            return conn;
        }catch(Exception ex){
            System.out.println("ERRO: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<AgendaMorador> getAgendarMoradorList(){
        ObservableList<AgendaMorador> agendaList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM agendamento";

        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            AgendaMorador agendamento;

            while(rs.next()){
                agendamento = new AgendaMorador(rs.getString("nome"), rs.getInt("bloco"), rs.getInt("numApto"), rs.getString("dia"));
                agendaList.add(agendamento);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return agendaList;
    }

    public void mostrarAgenda(){
        ObservableList<AgendaMorador> list = getAgendarMoradorList();

        colMorador.setCellValueFactory(new PropertyValueFactory<AgendaMorador, String>("nome"));
        colBloco.setCellValueFactory(new PropertyValueFactory<AgendaMorador, Integer>("bloco"));
        colNumApto.setCellValueFactory(new PropertyValueFactory<AgendaMorador, Integer>("numApto"));
        colData.setCellValueFactory(new PropertyValueFactory<AgendaMorador, String>("dia"));

        tvAgendaPiscina.setItems(list);
    }

    private void insertSalvar(){
        String query = "INSERT INTO agendamento VALUES (" + tfNome.getText() + "','" + tfApto.getText() + "','" + tfBloco.getText() + "'," + tfData.getText() + ")";
        executeQuery(query);
        mostrarAgenda();
    }

    private void updateAlterar(){
        String query = "UPDATE agendamento SET bloco = '" + tfBloco.getText() + "', numeroApto = '" + tfApto + "', dia = " + tfData.getText() + " WHERE nomeMorador ='" + tfNome.getText() + "";
        executeQuery(query);
        mostrarAgenda();
    }

    private void deleteExcluir(){
        String query = "DELETE FROM agendamento WHERE nomeMorador =" + tfNome.getText() + "";
        executeQuery(query);
        mostrarAgenda();
    }

    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st;

        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}


