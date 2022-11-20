package rest.koios.client.backend.api.block;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import rest.koios.client.backend.api.base.Result;
import rest.koios.client.backend.api.base.exception.ApiException;
import rest.koios.client.backend.api.block.model.Block;
import rest.koios.client.backend.api.block.model.BlockInfo;
import rest.koios.client.backend.api.block.model.BlockTxHash;
import rest.koios.client.backend.factory.BackendFactory;
import rest.koios.client.backend.factory.options.Limit;
import rest.koios.client.backend.factory.options.Options;
import rest.koios.client.backend.factory.options.filters.Filter;
import rest.koios.client.backend.factory.options.filters.FilterType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BlockServicePreprodIntegrationTest {

    private BlockService blockService;

    @BeforeAll
    public void setup() {
        blockService = BackendFactory.getKoiosPreprodService().getBlockService();
    }

    @Test
    void getLatestBlockTest() throws ApiException {
        Result<Block> blockResult = blockService.getLatestBlock();
        Assertions.assertTrue(blockResult.isSuccessful());
        Assertions.assertNotNull(blockResult.getValue());
        log.info(blockResult.getValue().toString());
    }

    @Test
    void getSpecificBlockTest() throws ApiException {
        Result<List<Block>> blockResult = blockService.getBlockList(Options.builder().option(Filter.of("block_height", FilterType.EQ, "300113")).build());
        log.info(blockResult.toString());
        Assertions.assertTrue(blockResult.isSuccessful());
        Assertions.assertNotNull(blockResult.getValue());
        log.info(blockResult.getValue().toString());
    }

    @Test
    void getBlockListLimitTest() throws ApiException {
        Options options = Options.builder().option(Limit.of(10)).build();
        Result<List<Block>> blockListResult = blockService.getBlockList(options);
        Assertions.assertTrue(blockListResult.isSuccessful());
        Assertions.assertNotNull(blockListResult.getValue());
        log.info(blockListResult.getValue().toString());
        assertEquals(10, blockListResult.getValue().size());
    }

    @Test
    void getBlockInformationTest() throws ApiException {
        String hash = "065b9f0a52b3d3897160a065a7fe2bcb64b2bf635937294ade457de6a7bfd2a4";
        Result<BlockInfo> blockInformationResult = blockService.getBlockInformation(hash);
        Assertions.assertTrue(blockInformationResult.isSuccessful());
        Assertions.assertNotNull(blockInformationResult.getValue());
        log.info(blockInformationResult.getValue().toString());
    }

    @Test
    void getBlockInformationBadRequestTest() {
        String hash = "test";
        ApiException exception = assertThrows(ApiException.class, () -> blockService.getBlockInformation(hash));
        assertInstanceOf(ApiException.class, exception);
    }

    @Test
    void getBlocksInformationTest() throws ApiException {
        String hash = "065b9f0a52b3d3897160a065a7fe2bcb64b2bf635937294ade457de6a7bfd2a4";
        Result<List<BlockInfo>> blockInformationResult = blockService.getBlocksInformation(List.of(hash),Options.EMPTY);
        Assertions.assertTrue(blockInformationResult.isSuccessful());
        Assertions.assertNotNull(blockInformationResult.getValue());
        log.info(blockInformationResult.getValue().toString());
    }

    @Test
    void getBlocksInformationBadRequestTest() {
        String hash = "test";
        ApiException exception = assertThrows(ApiException.class, () -> blockService.getBlocksInformation(List.of(hash),Options.EMPTY));
        assertInstanceOf(ApiException.class, exception);
    }

    @Test
    void getBlockTransactionsTest() throws ApiException {
        String hash = "065b9f0a52b3d3897160a065a7fe2bcb64b2bf635937294ade457de6a7bfd2a4";
        Result<List<BlockTxHash>> blockTransactionsResult = blockService.getBlockTransactions(List.of(hash), Options.EMPTY);
        Assertions.assertTrue(blockTransactionsResult.isSuccessful());
        Assertions.assertNotNull(blockTransactionsResult.getValue());
        log.info(blockTransactionsResult.getValue().toString());
    }

    @Test
    void getBlockTransactionsBadRequestTest() {
        String hash = "test";
        ApiException exception = assertThrows(ApiException.class, () -> blockService.getBlockTransactions(List.of(hash), Options.EMPTY));
        assertInstanceOf(ApiException.class, exception);
    }
}
