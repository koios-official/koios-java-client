package com.reina.koios.client.backend.api.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountInfo {

    /**
     * Account Status
     */
    private String status;

    /**
     * Delegated Pool
     */
    private String delegatedPool;

    /**
     * Account Balance
     */
    private Long totalBalance;

    /**
     * Utxo Balance
     */
    private Long utxo;

    /**
     * Rewards Balance
     */
    private Long rewards;

    /**
     * Withdrawals Amount
     */
    private Long withdrawals;

    /**
     * Rewards Available Amount
     */
    private Long rewardAvailable;

    /**
     * Reserves Amount
     */
    private Long reserves;

    /**
     * Treasury Amount
     */
    private Long treasury;
}