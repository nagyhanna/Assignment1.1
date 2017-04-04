package database;

/**
 * Created by hanna on 25.03.2017.
 */
public class DBConnectionFactory {


    public JDBConnectionWrapper getConnectionWrapper(boolean test){

        if(test){

            return new JDBConnectionWrapper(Constants.Schemas.TEST);


        }
         return new JDBConnectionWrapper(Constants.Schemas.PRODUCTION);

    }

}
