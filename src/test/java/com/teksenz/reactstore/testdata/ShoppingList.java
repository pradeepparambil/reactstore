package com.teksenz.reactstore.testdata;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingList {
    private String productName;
    private String quantity;
    private String customerName;
    private String creditcard;
}
