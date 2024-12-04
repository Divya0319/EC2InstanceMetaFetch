package com.fastturtle.ec2instancemetafetch.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeftRotateArrayRequest {

    @Setter
    private int[] arr;

    private int d;
}
