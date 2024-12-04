package com.fastturtle.ec2instancemetafetch.models;

import com.fastturtle.ec2instancemetafetch.utils.LinkedList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonElementsInLinkedListsRequest {
    private LinkedList<Integer> linkedList1;
    private LinkedList<Integer> linkedList2;

}
