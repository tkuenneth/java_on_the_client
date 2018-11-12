package javax.jnlp;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A trivial and incomplete implementation of javax.jnlp.BasicService.
 *
 * @author Thomas Kuenneth
 */
public class BasicServiceImpl implements BasicService {

    private static final Logger LOGGER = Logger.getLogger(BasicServiceImpl.class.getName());

    @Override
    public boolean showDocument(URL url) {
        if (isWebBrowserSupported()) {
            try {
                Desktop.getDesktop().browse(url.toURI());
                return true;
            } catch (IOException | URISyntaxException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public java.net.URL getCodeBase() {
        return null;
    }

    @Override
    public boolean isOffline() {
        return false;
    }

    @Override
    public boolean isWebBrowserSupported() {
        return Desktop.isDesktopSupported();
    }
}
