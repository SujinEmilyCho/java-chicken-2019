import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    private final static List<Table> tables = TableRepository.tables();
    private final static List<Menu> menus = MenuRepository.menus();

    public static void main(String[] args) {
        final int ORDER = 1;
        final int PAY = 2;
        final int PROGRAM_END = 3;
        int function = 0;
        do {
            OutputView.printMainScreen();
            function = InputView.inputFunctionNumber();
            if (function == ORDER) {
                order();
            }
            if (function == PAY) {
                payment();
            }
        } while (function != PROGRAM_END);
    }

    private static void order() {
        Table selectedTable = selectTable();
        Menu menu = selectMenu();
        int quantity = selectQuantity();
        selectedTable.addOrder(new Order(selectedTable, menu, quantity));
    }

    private static Table selectTable() {
        OutputView.printTables(tables);
        final int tableNumber = InputView.inputTableNumber();
        for (Table table : tables) {
            if (table.equals(tableNumber)) {
                return table;
            }
        }
        return null;        // 예외 처리 때 수정하기
    }

    private static Menu selectMenu() {
        OutputView.printMenus(menus);
        int menuNumber = InputView.inputMenu();
        for (Menu menu : menus) {
            if (menu.equals(menuNumber)) {
                return menu;
            }
        }
        return null;        // 예외 처리 때 수정하기
    }

    private static int selectQuantity() {
        return InputView.inputQuantity();
    }

    private static void payment() {
        OutputView.printTables(tables);
        int tableNumber = InputView.inputTableNumber();
        for (Table table : tables) {
            if (table.equals(tableNumber)) {
                table.showOrders();
                table.pay();
            }
        }
    }
}
