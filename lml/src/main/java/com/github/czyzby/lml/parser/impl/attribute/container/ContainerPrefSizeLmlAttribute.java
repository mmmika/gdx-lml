package com.github.czyzby.lml.parser.impl.attribute.container;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.tag.LmlTag;
import com.github.czyzby.lml.util.LmlUtilities;

/** See {@link Container#prefSize(com.badlogic.gdx.scenes.scene2d.ui.Value)}. If container is in a table cell, it also
 * calls {@link Cell#prefSize(com.badlogic.gdx.scenes.scene2d.ui.Value)}. See
 * {@link LmlUtilities#parseHorizontalValue(LmlParser, LmlTag, com.badlogic.gdx.scenes.scene2d.Actor, String)} and
 * {@link LmlUtilities#parseVerticalValue(LmlParser, LmlTag, com.badlogic.gdx.scenes.scene2d.Actor, String)} for more
 * info on value parsing. Mapped to "prefSize".
 *
 * @author MJ */
public class ContainerPrefSizeLmlAttribute extends AbstractSharedContainerAndCellLmlAttribute {
    @Override
    protected void applyToContainer(final LmlParser parser, final LmlTag tag, final Container<?> actor,
            final String rawAttributeData) {
        actor.prefWidth(LmlUtilities.parseHorizontalValue(parser, tag.getParent(), actor, rawAttributeData));
        actor.prefHeight(LmlUtilities.parseVerticalValue(parser, tag.getParent(), actor, rawAttributeData));
    }

    @Override
    protected void applyToCell(final Container<?> actor, final Cell<?> cell) {
        cell.prefWidth(actor.getPrefWidthValue());
        cell.prefHeight(actor.getPrefHeightValue());
    }
}
