package com.mcopue.sfconnector.services;

import com.mcopue.sfconnector.SecurityVariables;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Will be used later for graph generator.
 */
@Component
@Service

public class State {
    public State() {
    }

    private StringBuilder sb = new StringBuilder();

    @Getter @Setter private String userId = "spring test";
    @Getter @Setter private List<String> stateList = new ArrayList<>();

    @PostConstruct
    private void initList() {
        stateList.add("{\"date\":\"2020-05-05\",\"terms\":\"https://currencylayer.com/terms\",\"success\":true,\"privacy\":\"https://currencylayer.com/privacy\",\"historical\":true,\"source\":\"USD\",\"timestamp\":1588723199,\"quotes\":{\"USDMXN\":23.97788,\"USDPLN\":4.18625,\"USDAUD\":1.554243,\"USDUSD\":1,\"USDCAD\":1.404245}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2020-04-01\",\"timestamp\":1585785599,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.64181,\"USDCAD\":1.415325,\"USDPLN\":4.19705,\"USDMXN\":24.223031}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2020-03-01\",\"timestamp\":1583107199,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.534715,\"USDCAD\":1.34081,\"USDPLN\":3.92252,\"USDMXN\":19.82564}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2020-02-01\",\"timestamp\":1580601599,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.493875,\"USDCAD\":1.323485,\"USDPLN\":3.872904,\"USDMXN\":18.843604}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2020-01-01\",\"timestamp\":1577923199,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.42469,\"USDCAD\":1.29755,\"USDPLN\":3.79325,\"USDMXN\":18.913803}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-12-01\",\"timestamp\":1575244799,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.478315,\"USDCAD\":1.32848,\"USDPLN\":3.912615,\"USDMXN\":19.53925}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-11-01\",\"timestamp\":1572652799,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.447404,\"USDCAD\":1.31595,\"USDPLN\":3.80815,\"USDMXN\":19.110404}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-10-01\",\"timestamp\":1569974399,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.490795,\"USDCAD\":1.32195,\"USDPLN\":4.00415,\"USDMXN\":19.818798}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-09-01\",\"timestamp\":1567382399,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.486604,\"USDCAD\":1.33195,\"USDPLN\":3.97975,\"USDMXN\":20.108796}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-08-01\",\"timestamp\":1564703999,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.469905,\"USDCAD\":1.321435,\"USDPLN\":3.88915,\"USDMXN\":19.225299}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-07-01\",\"timestamp\":1562025599,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.436201,\"USDCAD\":1.31345,\"USDPLN\":3.75625,\"USDMXN\":19.102399}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-06-01\",\"timestamp\":1559433599,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.440504,\"USDCAD\":1.35435,\"USDPLN\":3.834404,\"USDMXN\":19.615504}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-05-01\",\"timestamp\":1556755199,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.42565,\"USDCAD\":1.343945,\"USDPLN\":3.82465,\"USDMXN\":18.997703}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-04-01\",\"timestamp\":1554163199,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.40656,\"USDCAD\":1.33135,\"USDPLN\":3.83465,\"USDMXN\":19.174298}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-03-01\",\"timestamp\":1551484799,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.411804,\"USDCAD\":1.32965,\"USDPLN\":3.78765,\"USDMXN\":19.267204}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-02-01\",\"timestamp\":1549065599,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.37875,\"USDCAD\":1.31025,\"USDPLN\":3.742404,\"USDMXN\":19.100804}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2019-01-01\",\"timestamp\":1546387199,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.41893,\"USDCAD\":1.362635,\"USDPLN\":3.74135,\"USDMXN\":19.633745}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2018-12-01\",\"timestamp\":1543708799,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.366504,\"USDCAD\":1.32955,\"USDPLN\":3.793038,\"USDMXN\":20.397204}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2018-11-01\",\"timestamp\":1541116799,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.38795,\"USDCAD\":1.309105,\"USDPLN\":3.790602,\"USDMXN\":20.176799}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2018-10-01\",\"timestamp\":1538438399,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.383601,\"USDCAD\":1.281265,\"USDPLN\":3.70245,\"USDMXN\":18.71205}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2018-09-01\",\"timestamp\":1535846399,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.390604,\"USDCAD\":1.30475,\"USDPLN\":3.708704,\"USDMXN\":19.084104}}");
        stateList.add("{\"success\":true,\"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\"historical\":true,\"date\":\"2018-08-01\",\"timestamp\":1533167999,\"source\":\"USD\",\"quotes\":{\"USDUSD\":1,\"USDAUD\":1.350355,\"USDCAD\":1.299905,\"USDPLN\":3.65465,\"USDMXN\":18.593994}}");
    };

    public void updateStateList(String record) {
        System.out.println("State list added record: " + record);
        stateList.add(record);
    }

}
