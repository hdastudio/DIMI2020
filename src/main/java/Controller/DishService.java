package Controller;

import DB.DatabaseConnection;
import Model.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DishService {
    private final Scanner scan = new Scanner(System.in);
    private final List<Dish> arrayDish = new ArrayList<>();

    private Connection connection = DatabaseConnection.getConnection();

    public DishService() {
        createDish();
    }

    public void createDish() {
        Dish soup = new Dish(10, "Кровавая Мэри", "свекла, помидор, картофель, капуста, перец", 299);
        Dish beverage = new Dish(20, "Голубая Устрица", "Мартини, 2 кубика льда, коньяк, сироп", 149.99);
        Dish burger = new Dish(30, "Большой Папочка", "Лист салата, кетчунез, сырок, помидорка, огуречик, горчичка, двойная отбивная", 200.00);
        Dish salad = new Dish(40, "Цезарь", "Курица, пармезан, листы салата, помидорка чиррик, сметано-майонезный соус", 250.00);
        arrayDish.add(soup);
        arrayDish.add(beverage);
        arrayDish.add(burger);
        arrayDish.add(salad);
    }

    public void addDish() throws SQLException {
        System.out.println("Введите id: ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.println("Введите имя: ");
        String name = scan.nextLine();
        System.out.println("Введите описание: ");
        String description = scan.nextLine();
        System.out.println("Введите цену: ");
        double price = Double.parseDouble(scan.nextLine());

        PreparedStatement stm = connection.prepareStatement("INSERT INTO dishes (id, name, description, price) VALUES (?,?,?,?)");
        stm.setInt(1, id);
        stm.setString(2, name);
        stm.setString(3, description);
        stm.setDouble(4, price);
        stm.execute();
        stm.close();
    }

    public void editDish() throws SQLException {
        displayDish();
        System.out.println("Введите id изменяемого блюда: ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.println("Введите измененное имя: ");
        String name = scan.nextLine();
        System.out.println("Введите измененное описание: ");
        String description = scan.nextLine();
        System.out.println("Введите изменённую цену: ");
        double price = Double.parseDouble(scan.nextLine());

        PreparedStatement stm = connection.prepareStatement("UPDATE dishes SET name = ?, description = ?, price = ? WHERE id = ?");
        stm.setString(1, name);
        stm.setString(2, description);
        stm.setDouble(3, price);
        stm.setInt(4, id);
        stm.execute();
        stm.close();

    }

    public void removeDish() throws SQLException {
        displayDish();
        System.out.println("Введите id продукта: ");
        int id = Integer.parseInt(scan.nextLine());

        PreparedStatement stm = connection.prepareStatement("DELETE FROM dishes WHERE id = ?");
        stm.setInt(1, id);
        stm.execute();
        stm.close();
    }


    public void displayDish() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM dishes");
        ResultSet res = stm.executeQuery();
        while (res.next()) {
            Dish dh = new Dish(res.getInt(1),res.getString(2),res.getString(3),res.getDouble(4));
            System.out.println(dh.toString());
        }
    }
}

