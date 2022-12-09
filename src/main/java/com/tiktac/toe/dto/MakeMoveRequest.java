package com.tiktac.toe.dto;

import lombok.Getter;

@Getter
public class MakeMoveRequest {
    private long gameId;
    private int fieldId;
}
