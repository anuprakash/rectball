package es.danirod.rectball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

import es.danirod.rectball.screens.*;
import es.danirod.rectball.settings.Scores;
import es.danirod.rectball.settings.Settings;

/**
 * Main class for the game.
 */
public class RectballGame extends Game {

    public static final String VERSION = "Rectball v0.0.7";

    private Map<Integer, AbstractScreen> screens;

    public Settings settings;

    public Scores scores;

    public AssetManager manager;

    @Override
    public void create() {
        this.screens = new HashMap<>();
        this.addScreen(new GameScreen(this));
        this.addScreen(new GameOverScreen(this));
        this.addScreen(new WelcomeScreen(this));
        this.addScreen(new SettingsScreen(this));
        this.addScreen(new LoadingScreen(this));

        manager = new AssetManager();
        manager.load("board/normal.png", Texture.class);
        manager.load("board/colorblind.png", Texture.class);
        manager.load("sound/fail.wav", Sound.class);
        manager.load("sound/gameover.wav", Sound.class);
        manager.load("sound/select.wav", Sound.class);
        manager.load("sound/success.wav", Sound.class);
        manager.load("sound/unselect.wav", Sound.class);
        manager.load("ui/switch.png", Texture.class);
        manager.load("ui/button.png", Texture.class);
        manager.load("scores.png", Texture.class);
        manager.load("timer.png", Texture.class);
        manager.load("gameover.png", Texture.class);

        for (Map.Entry<Integer, AbstractScreen> screen : screens.entrySet()) {
            screen.getValue().load();
        }

        settings = new Settings(Gdx.app.getPreferences("rectball"));
        scores = new Scores();

        setScreen(5);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }

    /**
     * Show the screen with this ID.
     * @param id  the ID of the screen to use.
     */
    public void setScreen(int id) {
        this.setScreen(screens.get(id));
    }

    /**
     * Add a screen to the map of Strings.
     * @param screen
     */
    public void addScreen(AbstractScreen screen) {
        screens.put(screen.getID(), screen);
    }
}