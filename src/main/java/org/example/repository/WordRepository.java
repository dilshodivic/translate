package org.example.repository;

import org.example.db.Database;
import org.example.dto.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class WordRepository {



    public Word getWordByName(String word) {
        try {
            Connection connection = Database.getConnection();
            String sql = String.format("Select * from word where english = '%s'", word);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String english = resultSet.getString("english");
                String uzbek = resultSet.getString("uzbek");
                String description = resultSet.getString("description");
                LocalDateTime created_date = resultSet.getTimestamp("created_date").toLocalDateTime();

     Word wordAdd = new Word();
                wordAdd.setId(id);
                wordAdd.setWord(english);
                wordAdd.setUzbek(uzbek);
                wordAdd.setDescription(description);
                wordAdd.setLocalDateTime(created_date);
                return wordAdd;

            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public int save(Word wordAdd) {
        try (Connection connection = Database.getConnection()) {


            PreparedStatement statement = connection.prepareStatement(
                    "insert into word(english,uzbek,description) " +
                            "values (?,?,?)");
            statement.setString(1, wordAdd.getWord());
            statement.setString(2, wordAdd.getUzbek());
            statement.setString(3,wordAdd.getDescription());


            int n = statement.executeUpdate();
            return n;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public List<Word> getWordList() {
        try (Connection connection = Database.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from word order by created_date desc");

            return getTransactions(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }




    private List<Word> getTransactions(ResultSet resultSet)  {
        List<Word> wordList = new LinkedList<>();
        try {
            while (resultSet.next()) {
                Word wordlistAdd = new Word();
                wordlistAdd.setId(resultSet.getInt("id"));
                wordlistAdd.setWord(resultSet.getString("english"));
                wordlistAdd.setUzbek(resultSet.getString("uzbek"));
                wordlistAdd.setDescription(resultSet.getString("description"));
                wordlistAdd.setLocalDateTime(resultSet.getTimestamp("created_date").toLocalDateTime());
            wordList.add(wordlistAdd);
            }
            return wordList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Word> search(String word) {
        try (Connection connection = Database.getConnection()) {
            Statement statement = connection.createStatement();
            String query = ("select * from word where english ilike '%"+word+"%'");
            ResultSet resultSet = statement.executeQuery(query);

            return getTransactions(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public int delete(Integer id) {
        try {
            Connection connection = Database.getConnection();
            String sql = String.format("Delete from word where id = '%d'", id);
            Statement statement = connection.createStatement();
              return   statement.executeUpdate(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }



    public Word getWordBy(String text) {
        try {
            Connection connection = Database.getConnection();
            String sql = String.format("Select english from word where uzbek = '%s'", text);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                String english = resultSet.getString("english");

                Word wordAdd = new Word();

                wordAdd.setWord(english);

                return wordAdd;

            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }





    public Word equalsWord(String english) {
        try (Connection connection = Database.getConnection()) {

            Statement statement = connection.createStatement();
            String query = ("select * from word where english ilike '"+english+"'");
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){

            Word word = new Word();
            word.setWord(resultSet.getString("english"));
                return word;
            }



        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }

    public List<Word> getAnswerList() {
        try (Connection connection = Database.getConnection()) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select uzbek from word ");

        return getAnswer(resultSet);
    } catch (SQLException e) {
        e.printStackTrace();
        System.exit(-1);
    }
        return null;
    }

    private List<Word> getAnswer(ResultSet resultSet) {
        List<Word> wordList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Word wordlistAdd = new Word();
                wordlistAdd.setUzbek(resultSet.getString("uzbek"));
                wordList.add(wordlistAdd);
            }
            return wordList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}