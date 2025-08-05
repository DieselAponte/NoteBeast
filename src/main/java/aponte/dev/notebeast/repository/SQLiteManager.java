package aponte.dev.notebeast.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager {
    private static SQLiteManager instance;
    private static final String URL="jdbc:sqlite:src/main/resources/aponte/dev/notebeast/db/AppStorage_.db";
    private Connection connection;

    private SQLiteManager(){
        try{
            connection= DriverManager.getConnection(URL);
            InitializeTables(); //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized SQLiteManager getInstance() {
        if (instance == null) {
            instance = new SQLiteManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void InitializeTables(){
        //Creación de Tablas
        String createProjectTable = """
                CREATE TABLE IF NOT EXISTS Project(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT NOT NULL
                );
                """;
        String createProjectObjectiveTable = """
                CREATE TABLE IF NOT EXISTS ProjectObjective(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    project_id INTEGER NOT NULL,    
                    objective TEXT NOT NULL,
                    FOREIGN KEY (project_id) REFERENCES Project(id)
                );
                """;

        //Revisión de tablas de los documentos guardados
        String createProjectResourceLogTable = """
                CREATE TABLE IF NOT EXISTS ProjectResourceLog(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    project_id INTEGER NOT NULL,
                    path TEXT NOT NULL,
                    FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE CASCADE
                );
                """;

        String createProjectProgressLogTable = """
                CREATE TABLE IF NOT EXISTS ProjectProgressLog(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    project_id INTEGER NOT NULL,
                    timestamp TEXT NOT NULL,
                    currentObjective TEXT NOT NULL,
                    eventDescription TEXT NOT NULL,
                    statusComment TEXT NOT NULL,
                    FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE CASCADE
                    );
                """;
        String createTaskTable = """
                CREATE TABLE IF NOT EXISTS Task(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    status TEXT NOT NULL CHECK (status IN ('UNRESOLVED', 'RESOLVED')),
                    priority TEXT NOT NULL CHECK(priority IN ('LOW', 'MID', 'HIGH')),
                    deadline TEXT                 
                );
                """;
        String createNoteTable = """
                CREATE TABLE Note(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,                
                    documentPath TEXT NOT NULL,
                    createdAt TEXT NOT NULL,
                    updatedAt TEXT,
                    project_id INTEGER,
                    FOREIGN KEY (project_id) REFERENCES Project(id) ON DELETE SET NULL
                );
                """;
        String createUserConfigTable = """
                CREATE TABLE IF NOT EXISTS UserConfig(
                    theme TEXT NOT NULL,
                    fontSize INTEGER NOT NULL,
                    language TEXT NOT NULL,
                    notifications INTEGER NOT NULL CHECK (notifications IN (0,1)),
                    defaultStudyDuration INTEGER NOT NULL,
                    defaultBreakDuration INTEGER NOT NULL,
                    alertDaysInAdvance INTEGER NOT NULL
                );
                """;


        try(Statement statement = connection.createStatement()){
            statement.execute(createProjectTable);
            statement.execute(createProjectObjectiveTable);
            statement.execute(createProjectResourceLogTable);
            statement.execute(createProjectProgressLogTable);
            statement.execute(createTaskTable);
            statement.execute(createNoteTable);
            statement.execute(createUserConfigTable);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}

