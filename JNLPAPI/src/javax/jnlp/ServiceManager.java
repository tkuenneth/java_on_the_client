package javax.jnlp;

/**
 * A trivial implementation of javax.jnlp.ServiceManager.
 *
 * @author Thomas Kuenneth
 */
public class ServiceManager {

    public static Object lookup(String classname) {
        return new BasicServiceImpl();
    }
}
