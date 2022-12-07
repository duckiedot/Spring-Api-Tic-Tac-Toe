package com.tiktac.toe.dto;

import lombok.Getter;

@Getter
public class MakeMoveRequest {
    private Long gameId;
    private int rowId;
    private int columnId;
}
