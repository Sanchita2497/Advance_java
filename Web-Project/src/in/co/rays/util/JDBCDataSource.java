package in.co.rays.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

    private static JDBCDataSource jds;
    private ComboPooledDataSource cpds;

    private JDBCDataSource() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");

            cpds = new ComboPooledDataSource();
            cpds.setDriverClass(rb.getString("driver"));
            cpds.setJdbcUrl(rb.getString("url"));
            cpds.setUser(rb.getString("user"));
            cpds.setPassword(rb.getString("password"));
            cpds.setInitialPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static JDBCDataSource getInstance() {
        if (jds == null) {
            jds = new JDBCDataSource();
        }
        return jds;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().cpds.getConnection();
    }

	public static void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		
	}
}
