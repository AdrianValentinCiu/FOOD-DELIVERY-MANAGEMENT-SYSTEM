package DataAccess;

import java.io.*;
import java.util.logging.Logger;

public class Serializator {
    /**
     * This method is used to serialize data
     * @param file - the path to the file
     * @param data - the data written in the file
     */
    public void serialize(String file, Object data) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This method is used to deserialize data
     * @param file - the path to the file
     * @return - it returned the deserialized data as an Object type
     */
    public Object deserialize(String file) {
        Object data = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            data = in.readObject();
            in.close();
            fileIn.close();
        } catch (EOFException e) {
            Logger.getAnonymousLogger().fine("File is finished");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
        return data;
    }
}
