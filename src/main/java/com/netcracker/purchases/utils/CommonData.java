package com.netcracker.purchases.utils;

import com.netcracker.purchases.models.types.Purchase;

import java.util.ArrayList;
import java.util.List;

public final class CommonData {
    private CommonData() {
    }

    public static final List<Purchase> PURCHASES = new ArrayList<>();
    public static final boolean IS_JDBC_INSTALLED = OracleService.isJdbcInstalled();
    public static final String MSG_INPUT_ERR_VALUE = "Ошибка! Введено некоректное значение, повторите ввод.\n";
    public static final String MSG_MENU = "Меню:";
    public static final String MSG_ADD = "Добавить покупку";
    public static final String MSG_EDIT = "Редактировать покупку";
    public static final String MSG_SHOW = "Отобразить покупки";
    public static final String MSG_SAVE = "Сохранить покупки";
    public static final String MSG_DEL = "Удалить покупку";
    public static final String MSG_EXIT = "Выход";
    public static final String MSG_LIST_EMPTY_VALUE = "Список с покупками пуст!\n";
    public static final String MSG_ADD_SUCCESS = "Добавление покупки успешно завершено!\n";
    public static final String MSG_EDIT_SUCCESS = "Редактирование покупки успешно завершено!\n";
    public static final String MSG_SHOW_SUCCESS = "Список с покупками успешно отображён!\n";
    public static final String MSG_DEL_SUCCESS = "Удаление покупки успешно завершено!\n";
    public static final String MSG_APP_CLOSE = "Работа программы завершена!";
    public static final String MSG_INPUT_NUM_DEL = "Введите номер удаляемой покупки:";
    public static final String MSG_NUM = "Номер";
    public static final String MSG_ID = "ID";
    public static final String MSG_NAME = "Название";
    public static final String MSG_COUNT = "Количество";
    public static final String MSG_UNIT = "Еденицы измерения";
    public static final String MSG_COMMENT = "Комментарий";
    public static final String MSG_INPUT_NUM_EDIT = "Введите номер редактируемой покупки:";
    public static final String MSG_INPUT_PURCHASE_NAME = "Введите название покупки: ";
    public static final String MSG_INPUT_PURCHASE_COUNT = "Введите количество товара: ";
    public static final String MSG_INPUT_PURCHASE_UNIT = "Введите еденицы измерения количества товара: ";
    public static final String MSG_INPUT_PURCHASE_COMMENT = "Введите комментарий к покупке: ";
    public static final String MSG_DATA_SAVE = "Желаете сохранить данные?(Д/Н)";
    public static final String MSG_JDBC_NOT_FOUND = "Oracle JDBC не установлен! Работа с базой данных не возможна. " +
            "Попытка загрузить список покупок из файла.\n";
    public static final String MSG_SELECT_DATA_LOAD = "Желаете загрузить данные из файла или из базы данных?(Ф/Б)";
    public static final String MSG_LIST_LOAD_SUCCESS = "Список покупок успешно загружен!\n";
    public static final String MSG_LIST_LOAD_ERR = "При загрузке списка покупок возникли ошибки!\n";
    public static final String MSG_SELECT_DATA_SAVE = "Желаете сохранить данные в файл или в базу данных?(Ф/Б)";
    public static final String MSG_LIST_SAVE_SUCCESS = "Список с покупками успешно сохранён!\n";
    public static final String MSG_LIST_SAVE_ERR = "Непредвиденная ошибка при записи списка покупок!";

}
