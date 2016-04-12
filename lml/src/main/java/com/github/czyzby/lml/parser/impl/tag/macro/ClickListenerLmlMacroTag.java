package com.github.czyzby.lml.parser.impl.tag.macro;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.tag.LmlTag;

/** Attaches {@link ClickListener} to its actor parent. When the actor is clicked, content between macro tags will be
 * parsed using {@link LmlParser#parseTemplate(String)} and the returned actors will be added to the stage. This is very
 * useful for adding "delayed" actors creation that should occur only if certain event is detected. For example, this
 * shows a dialog when the button is clicked:
 *
 * <blockquote>
 *
 * <pre>
 * &lt;button&gt;
 *   &lt;:onClick&gt;
 *     &lt;dialog&gt;Message!&lt;/dialog&gt;
 *   &lt;/:onClick&gt;
 * &lt;/button&gt;
 * </pre>
 *
 * </blockquote>However, you might want to cache the actors to prevent from parsing it each time the event occurs. Also,
 * you might need to show the actors only if certain condition is met. Both of these functionalities are supported:
 *
 * <blockquote>
 *
 * <pre>
 * &lt;button&gt;
 *   &lt;:onClick if="$myMethod == 13" cache="true" button="1" &gt;
 *     &lt;dialog&gt;Message!&lt;/dialog&gt;
 *   &lt;/:onClick&gt;
 * &lt;/button&gt;
 * </pre>
 *
 * </blockquote>In the example above, the actors will be displayed only if the result of myMethod is 13. Also, actors
 * will be cached after first parsing ("cache" attribute). The listener will be attached only to button with ID of 1
 * ("button" attribute).
 *
 * @author MJ */
public class ClickListenerLmlMacroTag extends AbstractListenerLmlMacroTag {
    public static final String BUTTON_ATTRIBUTE = "button";

    public ClickListenerLmlMacroTag(final LmlParser parser, final LmlTag parentTag, final StringBuilder rawTagData) {
        super(parser, parentTag, rawTagData);
    }

    @Override
    protected void attachListener(final Actor actor) {
        final ClickListener listener = new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                doOnEvent(actor);
            }
        };
        if (hasAttribute(BUTTON_ATTRIBUTE)) {
            final int button = getParser().parseInt(getAttribute(BUTTON_ATTRIBUTE), actor);
            listener.setButton(button);
        }
        actor.addListener(listener);
    }

    @Override
    public String[] getExpectedAttributes() {
        return new String[] { IF_ATTRIBUTE, CACHE_ATTRIBUTE, BUTTON_ATTRIBUTE };
    }
}
