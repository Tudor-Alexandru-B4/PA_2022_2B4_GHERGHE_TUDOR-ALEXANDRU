package lab3;

public interface Storage {
    int getStorage();
    void setStorage(int inputStorage);

    default int toMegaBytes(){
        return 1024 * getStorage();
    }

    default int toKiloBytes(){
        return 1048576 * getStorage();
    }

    default long toBytes(){
        return 1073741824L * getStorage();
    }
}
