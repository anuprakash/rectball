/*
 * This file is part of Rectball.
 * Copyright (C) 2015 Dani Rodríguez.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.danirod.rectball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import es.danirod.rectball.RectballGame;
import es.danirod.rectball.listeners.ScreenJumper;

public class GameOverScreen extends AbstractScreen {

    public GameOverScreen(RectballGame game) {
        super(game);
    }

    @Override
    public void setUpInterface(Table table) {
        // Set up the label data.
        String lastScore = Integer.toString(game.getCurrentGame().getScore());
        while (lastScore.length() < 4)
            lastScore = "0" + lastScore;
        String aliveTime = Integer.toString(Math.round(game.getCurrentGame().getTime()));
        String highScore = Long.toString(game.scores.getHighestScore());

        // Last score.
        Label highScoreLabel = new Label(lastScore, game.getSkin(), "monospace");
        highScoreLabel.setFontScale(10f);
        table.add(new Label("GAME OVER", game.getSkin())).colspan(2).expandX().row();
        table.add(highScoreLabel).expand().colspan(2).align(Align.center).row();

        // Alive time.
        Drawable clock = game.getSkin().newDrawable("iconClock");
        table.add(new Image(clock)).size(80).expandX().align(Align.right).padRight(20);
        table.add(new Label(aliveTime, game.getSkin())).expandX().align(Align.left).padLeft(20).row();

        // High score.
        Drawable crown = game.getSkin().newDrawable("iconCrown");
        table.add(new Image(crown)).size(80).expandX().align(Align.right).padRight(20);
        table.add(new Label(highScore, game.getSkin())).expandX().align(Align.left).padLeft(20).row();

        // Add replay button.
        TextButton replay = new TextButton("Replay", game.getSkin());
        replay.addCaptureListener(new ScreenJumper(game, Screens.GAME));
        table.add(replay).colspan(2).fillX().height(100).padTop(30).row();

        // Add menu button.
        TextButton menu = new TextButton("Menu", game.getSkin());
        menu.addCaptureListener(new ScreenJumper(game, Screens.MAIN_MENU));
        table.add(menu).colspan(2).fillX().height(100).padTop(30).row();
    }

    @Override
    public int getID() {
        return Screens.GAME_OVER;
    }



}
