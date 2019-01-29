package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jdk.nashorn.internal.objects.Global;

import java.sql.*;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

public class Controller{
    public TextArea searchOutput;
    public TextField artistText;
    public TextField albumText;
    public TextField songText;
    public TextArea triviaOutput;
    ArrayList<String> finalStrings = new ArrayList();

    @FXML

    private static Connection connectSql() throws SQLException, IOException{
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        String serverName = "csor12c.dhcp.bsu.edu";
        String portNumber = "1521";
        String sid = "or12cdb";
        String url = "jdbc:oracle:thin:@" + serverName+ ":"+ portNumber+ ":"+sid;

        Connection conn = DriverManager.getConnection(url, "mjlewis4", "2519");

        return conn;
    }

    public void albumNamePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String albumSearch=albumText.getText();
        albumSearch= albumSearch.toUpperCase();
        String queryString = getQueryString("Album_Names, Genre, A_Duration, Album_art", "ALBUM", "Album_Names", albumSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    public void albumGenrePress(ActionEvent actionEvent)  throws SQLException, IOException{
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String albumSearch=albumText.getText();
        albumSearch= albumSearch.toLowerCase();
        String queryString = getQueryString("Album_Names, Genre, A_Duration, Album_art", "ALBUM", "Genre", albumSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        System.out.println(output);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    public void artistFirstNamePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String artistSearch=artistText.getText();
        String queryString = getQueryString("Artist_name, Firstname, Lastname, Biography", "ARTISTS", "Firstname", artistSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    public void artistLastNamePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String artistSearch=artistText.getText();
        String queryString = getQueryString("Artist_name, Firstname, Lastname, Biography", "ARTISTS", "Lastname", artistSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        conn.close();
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    public void artistStageNamePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String artistSearch=artistText.getText();
        String queryString = getQueryString("Artist_name, Firstname, Lastname, Biography", "ARTISTS", "Artist_name", artistSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);

        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    //Artist age may or may not actually be in the database? if it is not we have to add it to all of these calls
    public void artistAgePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String artistSearch=artistText.getText();
        String queryString = getQueryString("Artist_name, Firstname, Lastname, Biography", "ARTISTS", "Age", artistSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }

    public void artistBioPress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String artistSearch=artistText.getText();
        String queryString = getQueryString("Artist_name, Firstname, Lastname, Biography", "ARTISTS", "Biography", artistSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }


    public void songNamePress(ActionEvent actionEvent)  throws SQLException, IOException{
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String songSearch=songText.getText();
        String queryString = getQueryString("SONG_NAME, Song_duration, Filetype, Tags", "SONG", "SONG_NAME", songSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();
    }


    public void songDurationPress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String songSearch=songText.getText();
        String queryString = getQueryString("SONG_NAME, Song_duration, Filetype, Tags", "SONG", "Song_duration", songSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();

    }

    /* SONG DOES NOT HAVE GENRE< ONLY HAS TAGS NEEDS TO BE RENAMED HERE AND IN THE FXML*/

    public void songGenrePress(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String songSearch=songText.getText();
        songSearch=songSearch.toLowerCase();
        String queryString = getQueryString("SONG_NAME, Song_duration, Filetype, Tags", "SONG", "Tags", songSearch);
        ResultSet results = statement.executeQuery(queryString);
        finalStrings=printResults(results);
        String output= combineString(finalStrings);
        results.close();
        statement.close();
        conn.close();
        searchOutput.setText(output);
        finalStrings.clear();

    }

    //********************************************************************************* Tab Change
    public void longestSong(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String queryString = "SELECT MAX(Song_duration) AS Maximum FROM SONG";
        ResultSet results = statement.executeQuery(queryString);
        String output = printSpecialResults(results);
        results.close();
        statement.close();
        conn.close();
        triviaOutput.setText(output);
    }

    public void longestAlbum(ActionEvent actionEvent) throws SQLException, IOException {
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String queryString = "SELECT MAX(A_duration) AS Maximum FROM ALBUM";
        ResultSet results = statement.executeQuery(queryString);
        String output = printSpecialResults(results);
        results.close();
        statement.close();
        conn.close();
        triviaOutput.setText(output);
    }

    public void shortestAlbum(ActionEvent actionEvent) throws SQLException, IOException{
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String queryString = "SELECT MIN(A_duration) AS Maximum FROM ALBUM";
        ResultSet results = statement.executeQuery(queryString);
        String output = printSpecialResults(results);
        results.close();
        statement.close();
        conn.close();
        triviaOutput.setText(output);
}


    // I think this one is not what we intended, need to switch to SUM(Song_duration) FROM SONG?
    public void totalDuration(ActionEvent actionEvent) throws SQLException, IOException{
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String queryString = "SELECT SUM(Total_listen_time) AS Maximum FROM LISTENER";
        ResultSet results = statement.executeQuery(queryString);
        String output = printSpecialResults(results);
        results.close();
        statement.close();
        conn.close();
        triviaOutput.setText(output);
    }

    public void highestPaid(ActionEvent actionEvent) throws SQLException, IOException{
        Connection conn = connectSql();
        Statement statement = conn.createStatement();
        String queryString = "SELECT MAX(Salary) AS Maximum FROM ARTISTS";
        ResultSet results = statement.executeQuery(queryString);
        String output = printSpecialResults(results);
        results.close();
        statement.close();
        conn.close();
        triviaOutput.setText(output);
    }



    //********************************************************************************** Backend to the backend

    public static String getQueryString(String select, String table, String column, String item){
        String newQueryString = "SELECT "+ select + " FROM " + table + " WHERE " + column + " = '" + item + "'";
        System.out.println(newQueryString);
        return newQueryString;
    }

    public static String printSpecialResults(ResultSet result) throws SQLException {
        String resultString = "";
        while(result.next()){
        resultString = result.getString("Maximum");}
        // IS PROBABLY GOING TO WORK？
        return resultString;
    }

    public ArrayList<String> printResults(ResultSet result) throws SQLException{
        //System.out.println();
        String resultString="error in printResult";
        while(result.next()){
            resultString = result.getString(1) + " " +
                    " " + result.getString(2)+" " + result.getString(3)
                    +" " + result.getString(4)+"\n";
            finalStrings.add(resultString);
        }
        return finalStrings;
    }
    public String combineString (ArrayList<String> results){
        String temp="";
        for (String i: results){
            temp = temp.concat(i+"\n");
        }
        System.out.println(temp);
        return temp;
    }

}
