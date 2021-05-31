package com.example.linebottest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexGravity;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class LineFlexCarousel implements Supplier<FlexMessage>{
    @Override
    public FlexMessage get() {
        final Bubble bubble1 = createBubble("Scary Movie",
                "Scary Movie Description",
                "https://upload.wikimedia.org/wikipedia/en/2/29/Movie_poster_for_%22Scary_Movie%22.jpg");
        final Bubble bubble2 = createBubble("Spongebob Movie",
                "Spongebob Movie Description",
                "https://m.media-amazon.com/images/M/MV5BOGYxYzZkMWQtNjJkMy00NTlkLWExNWMtOTNhMTg4MDcxNmU3XkEyXkFqcGdeQXVyMDk5Mzc5MQ@@._V1_.jpg");
        final Bubble seeMore = createSeeMoreBubble();
        final Carousel carousel = Carousel.builder()
                .contents(Arrays.asList(bubble1, bubble2, seeMore))
                .build();
        return new FlexMessage("Catalogue", carousel);
    }

    private Bubble createBubble(String title, String price, String imageURL) {
        final Image heroBlock = createHeroBlock(imageURL);
        final Box bodyBlock = createBodyBlock(title, price);
        final Box footerBlock = createFooterBlock();
        return Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
    }

    private Bubble createSeeMoreBubble() {
        return Bubble.builder()
                .body(Box.builder()
                        .layout(FlexLayout.VERTICAL)
                        .spacing(FlexMarginSize.SM)
                        .contents(Arrays.asList(
                                Button.builder()
                                        .flex(1)
                                        .gravity(FlexGravity.CENTER)
                                        .action(new URIAction("See more", "https://movie2freehd.com/"))
                                        .build()
                        )).build()
                )
                .build();
    }

    private Image createHeroBlock(String imageURL) {
        return Image.builder()
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .url(imageURL)
                .build();
    }

    private Box createBodyBlock(String title, String price) {
        final Text titleBlock = Text.builder()
                .text(title)
                .wrap(true)
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL).build();
        FlexComponent[] flexComponents = {titleBlock};
        List<FlexComponent> listComponent = new ArrayList<>(Arrays.asList(flexComponents));

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(listComponent)
                .build();
    }

    private Box createFooterBlock() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.XXL).build();
        final Button button = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#905c44")
                .action(new URIAction("Watch Movie", "https://movie2freehd.com/"))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(spacer, button))
                .build();
        
                
    }
}