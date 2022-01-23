package com.reina.koios.client.backend.api.block;

import com.reina.koios.client.backend.api.block.model.Block;
import com.reina.koios.client.backend.api.block.model.BlockInfo;
import com.reina.koios.client.backend.api.TxHash;
import com.reina.koios.client.backend.factory.BackendFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlockServiceIntegrationTest {

    private BlockService blockService;

    @BeforeAll
    public void setup() {
        blockService = BackendFactory.getKoiosTestnetService().getBlockService();
    }

    @Test
    void getBlockListTest() {
        Block[] blockList = blockService.getBlockList();
        log.info(Arrays.toString(blockList));
        Assertions.assertNotNull(blockList);
    }

    @Test
    void getBlockInformationTest() {
        String hash = "50a63ac54ccceb7de3f145e440b93842a7c2c2dab62e9fbd3bd1414585b483e9";
        BlockInfo[] blockInformation = blockService.getBlockInformation(hash);
        log.info(Arrays.toString(blockInformation));
        Assertions.assertNotNull(blockInformation);
        Assertions.assertEquals(hash,blockInformation[0].getHash());
    }

    @Test
    void getBlockTransactionsTest() {
        String hash = "50a63ac54ccceb7de3f145e440b93842a7c2c2dab62e9fbd3bd1414585b483e9";
        TxHash[] blockTransactions = blockService.getBlockTransactions(hash);
        log.info(Arrays.toString(blockTransactions));
        Assertions.assertNotNull(blockTransactions);
    }
}