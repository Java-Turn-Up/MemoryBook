package com.example.memorybook.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ReqFormatPosting {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel
    public static class BasicPostingInfo{
        @ApiModelProperty(example = "Book ID")
        @Schema(type = "String", name = "book_id")
        private String Book_id;
        private Long Creator_id;
        private String Title;
        private String Content;
        private Long Hit;
        private Long Like;
        private Long Dislike;
    }
}
