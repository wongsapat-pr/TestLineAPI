package com.example.linebottest.controller;

import java.time.Month;
import java.util.Arrays;
import java.util.function.Supplier;

import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Button;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Spacer;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexAlign;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;

public class LineFlexSalary implements Supplier<FlexMessage>{
        @Override
        public FlexMessage get() {
            final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
            final Box headerBlock = createBodyHeaderBox();
            final Box itemBlock = createBodyItemBlock();
            final Box summaryBlock = createBodySummaryBlock();
            final Box footerBlock = createBodyFooterBlock();
    
            final Box bodyBlock = Box.builder()
                    .layout(FlexLayout.VERTICAL)
                    .contents(Arrays.asList(
                            headerBlock,
                            separator,
                            itemBlock,
                            separator,
                            summaryBlock,
                            separator,
                            footerBlock))
                    .build();
    
            final Bubble bubble = Bubble.builder()
                    .body(bodyBlock)
                    .build();
            return new FlexMessage("ALT", bubble);
        }
    
        private Box createBodyHeaderBox() {
            final Text bodyHeaderText = Text.builder()
                    .text("สไปเดร์เอชอาร์เอ็ม")
                    .weight(Text.TextWeight.BOLD)
                    .color("#1db446")
                    .size(FlexFontSize.SM)
                    .build();
            final Text bodyTitleHeaderText = Text.builder()
                    .text("ใบเสร็จเงินเดือน")
                    .weight(Text.TextWeight.BOLD)
                    .size(FlexFontSize.XXL)
                    .margin(FlexMarginSize.MD)
                    .build();
            String month = "ม.ค";
                final Text bodyTitleHeaderDetail = Text.builder()
                    .text("เงินเดือนประจำเดือน "+ month)
                    .size(FlexFontSize.XS)
                    .color("#aaaaaa")
                    .wrap(true)
                    .build();
    
            return Box.builder()
                    .layout(FlexLayout.VERTICAL)
                    .contents(Arrays.asList(
                            bodyHeaderText,
                            bodyTitleHeaderText,
                            bodyTitleHeaderDetail))
                    .build();
        }
    
        private Box createBodyItemBlock() {
            final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
            final Box item1 = createItem("เงินเดือน", "$2.99");
            final Box item2 = createItem("เงินประจำตำแหน่ง", "$0.99");
            final Box item3 = createItem("เงินได้พิเศษ", "$3.33");
            return Box.builder()
                    .layout(FlexLayout.VERTICAL)
                    .margin(FlexMarginSize.XXL)
                    .spacing(FlexMarginSize.SM)
                    .contents(Arrays.asList(
                            item1,
                            item2,
                            item3))
                    .build();
        }
    
        private Box createBodySummaryBlock() {
            final Separator separator = Separator.builder().margin(FlexMarginSize.XXL).build();
            final Box items = createItem("เงินกู้สหกรณ์", "3");
            final Box total = createItem("เงินกู้องค์กร", "$7.31");
            final Box cash = createItem("เงินหักอื่นๆ", "$8.00");
            final Box change = createItem("สุทธิ", "$0.69");
            return Box.builder()
                    .layout(FlexLayout.VERTICAL)
                    .margin(FlexMarginSize.XXL)
                    .spacing(FlexMarginSize.SM)
                    .contents(Arrays.asList(
                            items,
                            total,
                            cash,
                            change))
                    .build();
        }
    
        private Box createBodyFooterBlock() {
            return Box.builder()
                    .layout(FlexLayout.HORIZONTAL)
                    .margin(FlexMarginSize.MD)
                    .contents(Arrays.asList(
                            Text.builder()
                                    .text("PAYMENT ID")
                                    .size(FlexFontSize.XS)
                                    .color("#aaaaaa")
                                    .flex(0)
                                    .build(),
                            Text.builder()
                                    .text("#743289384279")
                                    .size(FlexFontSize.XS)
                                    .color("#aaaaaa")
                                    .align(FlexAlign.END)
                                    .build()
                    ))
                    .build();
        }
    
        private Box createItem(String name, String price) {
            return Box.builder()
                    .layout(FlexLayout.HORIZONTAL)
                    .contents(Arrays.asList(
                            Text.builder()
                                    .text(name)
                                    .size(FlexFontSize.SM)
                                    .color("#555555")
                                    .flex(0)
                                    .build(),
                            Text.builder()
                                    .text(price)
                                    .size(FlexFontSize.SM)
                                    .color("#111111")
                                    .align(FlexAlign.END)
                                    .build()
                    )).build();
        }

}
