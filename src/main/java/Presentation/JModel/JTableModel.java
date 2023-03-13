package Presentation.JModel;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

public class JTableModel<T> {
    final Class<T> type = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public JTableModel() {
    }

    /**
     * This method is used to create the JTable with the titles
     * @return the model with the titles
     */
    private DefaultTableModel model() {
        Object[] columns = new Object[this.type.getDeclaredFields().length];
        int contor = 0;
        Field[] fields = this.type.getDeclaredFields();
        int length = fields.length;

        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            try {
                columns[contor++] = field.getName();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return new DefaultTableModel(columns, 0);
    }

    /**
     * This method is used to insert the data into the JTable
     * @param data - the data which has to be inserted into the JTable
     * @return the JTable with the inserted data
     */
    public DefaultTableModel modelData(List<T> data) {
        if (data != null && data.size() != 0) {
            DefaultTableModel model = this.model();
            Object[] rowData = new Object[data.get(0).getClass().getDeclaredFields().length];
            Iterator it = data.iterator();

            while (it.hasNext()) {
                T eachData = (T) it.next();
                int contor = 0;
                Field[] fields = eachData.getClass().getDeclaredFields();
                int length = fields.length;

                for (int i = 0; i < length; ++i) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    try {
                        rowData[contor++] = field.get(eachData);
                    } catch (IllegalAccessException | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }

                model.addRow(rowData);
            }

            return model;
        } else {
            return this.model();
        }
    }
}

