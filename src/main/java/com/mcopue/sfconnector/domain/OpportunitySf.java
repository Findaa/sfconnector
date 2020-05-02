package com.mcopue.sfconnector.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class OpportunitySf {
    @Getter @Setter private String Id;
    @Getter @Setter private String AccountId;
    @Getter @Setter private String Name;
    @Getter @Setter private String Amount;
    @Getter @Setter private Date CloseDate;
    @Getter @Setter private String Stage;

    public List<OpportunitySf> opps = new ArrayList<OpportunitySf>();

    public OpportunitySf(String id, String accountId, String name, String amount, Date closeDate, String stage) {
        Id = id;
        AccountId = accountId;
        Name = name;
        Amount = amount;
        CloseDate = closeDate;
        Stage = stage;
    }
}
