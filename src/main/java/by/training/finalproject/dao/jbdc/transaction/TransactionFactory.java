package by.training.finalproject.dao.jbdc.transaction;

public class TransactionFactory {
    private static final TransactionFactory instance = new TransactionFactory();
    private final Transaction entityTransaction = new EntityTransaction();

    private TransactionFactory() {
    }

    public static TransactionFactory getInstance() {
        return instance;
    }

    public Transaction getEntityTransaction() {
        return entityTransaction;
    }
}
