package pa4;

public class HashingWithProbing {

    public int hashTable[];
    public int TABLE_SIZE;
    public int size;
    public int garbage;

    public static final int EMPTY = -1;
    public static final int TOMBSTONE = -9;

    private int getHashValue(int val) {
        return (37 * val + 61) % TABLE_SIZE;
    }

    public HashingWithProbing(int tableSize) {
        TABLE_SIZE = tableSize;
        size = 0;
        garbage = 0;
        hashTable = new int[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = EMPTY;
        }
    }

    private void resize(int newTableSize) { // complete this method
        int oldTable[] = hashTable;
        TABLE_SIZE = newTableSize;
        size = 0;
        garbage = 0;
        hashTable = new int[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            hashTable[i] = EMPTY;
        }
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != EMPTY && oldTable[i] != TOMBSTONE) {
                insert(oldTable[i]);
            }
        }
    }

    public int search(int key) { // complete this method
        int hashValue = getHashValue(key);
        for (int i = 0; i < TABLE_SIZE; i++) {
            int index = (hashValue + i) % TABLE_SIZE;
            if (hashTable[index] == EMPTY) {
                return -1;
            }
            if (hashTable[index] == key) {
                return index;
            }
        }
        return -1;
    }

    public int insert(int val) { // complete this method
        if (size + garbage == TABLE_SIZE) {
            resize(TABLE_SIZE * 2);
        }
        int hashValue = getHashValue(val);
        for (int i = 0; i < TABLE_SIZE; i++) {
            int index = (hashValue + i) % TABLE_SIZE;
            if (hashTable[index] == val) {
                return -1;
            }
            if (hashTable[index] == EMPTY) {
                hashTable[index] = val;
                size++;
                return index;
            }
        }
        return -1;
    }

    public int remove(int val) { // complete this method
        int index = search(val);
        if (index < 0) {
            return -1;
        }
        hashTable[index] = TOMBSTONE;
        garbage++;
        size--;
        return index;

    }
}
