package ru.avalon.java.j30.labs;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Класс описывает представление о коде товара и отражает соответствующую 
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * Основной конструктор типа {@link ProductCode}
     * 
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание 
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, 
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) throws SQLException {
        /*
         * TODO #05 реализуйте конструктор класса ProductCode
         */
        this.code = set.getString("prod_code");
        this.discountCode = set.getString("discount_code").charAt(0);
        this.description = set.getString("description");
    }
    /**
     * Возвращает код товара
     * 
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }
    /**
     * Устанавливает код товара
     * 
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Возвращает код скидки
     * 
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }
    /**
     * Устанавливает код скидки
     * 
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    /**
     * Возвращает описание
     * 
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Устанавливает описание
     * 
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Хеш-функция типа {@link ProductCode}.
     *
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        return Objects.hash(code, discountCode, description);
    }
    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа
     * {@link ProductCode}
     *
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        /*
         * TODO #07 Реализуйте метод equals
         */
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProductCode) {
            ProductCode product = (ProductCode) obj;
            return code.equals(product.code) && discountCode == (product.discountCode)
                    && description.equals(product.description);
        }
        return false;
    }
    /**
     * Возвращает строковое представление кода товара.
     * 
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
        /*
         * TODO #08 Реализуйте метод toString
         */
        return "Product Code: code=" + code + ", discountCode=" + discountCode + ", description=" + description + ".";
    }
    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #09 Реализуйте метод getSelectQuery
         */
        return connection.prepareStatement(getQueryFromFile("resources/sql/select.all.sql"));
    }
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #10 Реализуйте метод getInsertQuery
         */
        return connection.prepareStatement(getQueryFromFile("resources/sql/insert.sql"));
    }
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException, IOException {
        /*
         * TODO #11 Реализуйте метод getUpdateQuery
         */
        return connection.prepareStatement(getQueryFromFile("resources/sql/update.sql"));
    }
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего 
     * все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * TODO #12 Реализуйте метод convert
         */
        Collection<ProductCode> productCodes = new LinkedList<>();
        while (set.next()) {
            ProductCode productCode = new ProductCode(set);
            productCodes.add(productCode);
        }
        return new ArrayList<>(productCodes);
    }
    /**
     * Сохраняет текущий объект в базе данных. 
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     * 
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException, IOException {
        /*
         * TODO #13 Реализуйте метод convert
         */
        try (PreparedStatement selectStatement = connection.prepareStatement(getQueryFromFile("resources/sql/select.sql"))){
            selectStatement.setString(1, this.code);
            selectStatement.setString(2, this.description);
            try (ResultSet set = selectStatement.executeQuery()){
                if (!set.next()) {
                    try(PreparedStatement insertStatement = getInsertQuery(connection)) {
                        insertStatement.setString(1, this.code);
                        insertStatement.setString(2, String.valueOf(this.discountCode));
                        insertStatement.setString(3, this.description);
                        insertStatement.executeUpdate();
                    }
                } else {
                    try (PreparedStatement updateStatement = getUpdateQuery(connection)) {
                        updateStatement.setString(1, this.code);
                        updateStatement.setString(2, String.valueOf(this.discountCode));
                        updateStatement.setString(3, this.description);
                        updateStatement.setString(4, this.code);
                        updateStatement.setString(5, this.description);
                        updateStatement.executeUpdate();
                    }
                }
            }
        }
    }
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link ProductCode}
     * 
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException, IOException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }

    private static String getQueryFromFile (String path) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(path);
             Reader reader = new InputStreamReader(stream);
             BufferedReader in = new BufferedReader(reader)) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line)
                        .append(System.lineSeparator());
            }
            return builder.toString();
        }
    }
}
