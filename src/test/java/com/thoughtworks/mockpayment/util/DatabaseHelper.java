package com.thoughtworks.mockpayment.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    public static void cleanDatabase(SqlSessionManager sqlSessionManager) throws SQLException {
        try (final SqlSession sqlSession = sqlSessionManager.openSession()) {
            final PreparedStatement statement = sqlSession.getConnection().prepareStatement("SET FOREIGN_KEY_CHECKS = 0;" +
                    "TRUNCATE DEPOSIT_ORDER;" + "TRUNCATE WITHDRAW_ORDER;" +
                    "SET FOREIGN_KEY_CHECKS = 1;");
            statement.setQueryTimeout(20);
            statement.execute();
            sqlSession.commit(true);
        }
    }
}
