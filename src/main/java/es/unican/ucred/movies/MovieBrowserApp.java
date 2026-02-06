package es.unican.ucred.movies;

import es.unican.ucred.movies.service.ITMDbService;
import es.unican.ucred.movies.service.TMDbService;
import es.unican.ucred.movies.ui.MainFrame;

import javax.swing.*;

/**
 * Main application class for the UCRED Movies Browser.
 */
public class MovieBrowserApp {
    public static void main(String[] args) {
        // Get API key from environment variable
        String apiKey = System.getenv("TMDB_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Error: TMDB_API_KEY environment variable not set");
            System.err.println("Please set your TMDb API key:");
            System.err.println("  export TMDB_API_KEY=your_api_key_here");
            System.exit(1);
        }

        // Create service
        ITMDbService tmdbService = new TMDbService(apiKey);

        // Launch UI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Use default look and feel
            }

            MainFrame frame = new MainFrame(tmdbService);
            frame.setVisible(true);
        });
    }
}
