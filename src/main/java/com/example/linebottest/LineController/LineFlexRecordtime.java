package com.example.linebottest.LineController;

import java.util.Arrays;
import java.util.function.Supplier;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class LineFlexRecordtime implements Supplier<FlexMessage>{
    @Override
    public FlexMessage get() {
        final Box headerBlock = createHeroBlock();
        final Box footerBlock = createFooterBox();

        final Bubble bubble = Bubble.builder()
                .header(headerBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("ALT", bubble);
    }
    private Box createHeroBlock() {
        final Text title = Text.builder()
                                .text("บักทึกการลงเวลา")
                                .weight(Text.TextWeight.BOLD)
                                .size(FlexFontSize.XL)
                                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.MD)
                .contents(Arrays.asList(title))
                                .build();
    }

    private Box createFooterBox() {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.XXL).build();
        final Button button = Button.builder()
                .style(Button.ButtonStyle.PRIMARY)
                .color("#905c44")
                // .action(new URIAction("เปิดใช้งานกล้อง", "https://line.me/R/nv/camera/"))
                .action(new URIAction("เปิดใช้งานกล้อง", "https://liff.line.me/"))
                .build();
        
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(spacer, button))
                .build();
    }

}
