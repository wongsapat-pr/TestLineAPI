package com.example.linebottest.controller;

import java.util.Arrays;
import java.util.function.Supplier;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Icon;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class LineFlexMessage implements Supplier<FlexMessage>{
    @Override
    public FlexMessage get() {
        final Image heroBlock = createHeroBlock();
        final Box bodyBlock = createBodyBlock();
        final Box footerBlock = createFooterBox();

        final Bubble bubble = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("ALT", bubble);
    }
    private Image createHeroBlock() {
        return Image.builder()
                .url("https://i.insider.com/5f578371e6ff30001d4e76be?width=1136&format=jpeg")
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .action(new URIAction("label", "https://movie2freehd.com/"))
                .build();
    }
    private Box createBodyBlock() {
        final Text title = Text.builder()
                .text("Recommend")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();
        final Box menus = createMenusBox();
        final Box recipe = createRecipeBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.MD)
                .contents(Arrays.asList(title, menus, recipe))
                .build();
    }
    private Box createRecipeBox() {
        final Box recipe = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.XS)
                .contents(Arrays.asList(
                        Text.builder()
                                .text("Movie Website")
                                .size(FlexFontSize.XS)
                                .color("#aaaaaa")
                                .flex(1)
                                .build())).build();
        return recipe;
    }

    private Box createMenusBox() {
        final Box menu1 = Box.builder()
                .layout(FlexLayout.BASELINE)
                .contents(Arrays.asList(
                        // Icon.builder()
                        // .url("https://2553d2b9.ngrok.io/regular.png").build(),
                        Text.builder().text("Harry Potter")
                                .weight(Text.TextWeight.BOLD)
                                .margin(FlexMarginSize.SM)
                                .flex(0)
                                .build(),
                        Text.builder().text("1")
                                .size(FlexFontSize.SM)
                                .align(FlexAlign.END)
                                .color("#aaaaaa")
                                .build()

                ))
                .build();
        final Box menu2 = Box.builder()
                .layout(FlexLayout.BASELINE)
                .contents(Arrays.asList(
                        // Icon.builder()
                        //         .url("https://2553d2b9.ngrok.io/large.png").build(),
                        Text.builder().text("Fantastic Beast")
                                .weight(Text.TextWeight.BOLD)
                                .margin(FlexMarginSize.SM)
                                .flex(0)
                                .build(),
                        Text.builder().text("2")
                                .size(FlexFontSize.SM)
                                .align(FlexAlign.END)
                                .color("#aaaaaa")
                                .build()

                ))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(menu1, menu2))
                .build();
    }

    private Box createFooterBox() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.XXL).build();
        final Button button = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#905c44")
                .action(new URIAction("Go to Website", "https://movie2freehd.com/"))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(spacer, button))
                .build();
    }

    
}
