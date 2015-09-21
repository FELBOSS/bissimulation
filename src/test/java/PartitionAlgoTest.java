import org.junit.Assert;
import org.junit.Test;
import org.usp.sfv.algorithm.PartitionAlgo;
import org.usp.sfv.domain.Block;
import org.usp.sfv.domain.partition.Partition;

import java.util.HashSet;

/**
 * App: bissimulationx
 * User: caiobos
 * Date: 9/21/15
 */
public class PartitionAlgoTest {

    @Test
    public void findComplementEquals(){
        HashSet<String> a = new HashSet<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Block ba = new Block(a);

        HashSet<String> b = new HashSet<>();
        b.add("3");
        b.add("4");
        Block bb = new Block(b);

        PartitionAlgo partitionAlgo = new PartitionAlgo(null);
        partitionAlgo.findComplement(ba, bb);
    }


    @Test
    public void findPartitionNoChange(){
        HashSet<String> a = new HashSet<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Block ba = new Block(a);

        HashSet<String> b = new HashSet<>();
        b.add("3");
        b.add("4");
        Block bb = new Block(b);

        PartitionAlgo partitionAlgo = new PartitionAlgo(null);
        partitionAlgo.findSubPartition(ba, bb);
    }


    @Test
    public void findPartitionPartial(){
        HashSet<String> a = new HashSet<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Block ba = new Block(a);

        HashSet<String> b = new HashSet<>();
        b.add("1");
        b.add("4");
        Block bb = new Block(b);

        PartitionAlgo partitionAlgo = new PartitionAlgo(null);
        partitionAlgo.findSubPartition(ba, bb);
    }


    @Test
    public void findPartitionAll(){
        HashSet<String> a = new HashSet<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Block ba = new Block(a);

        HashSet<String> b = new HashSet<>();
        b.add("1");
        b.add("2");
        Block bb = new Block(b);

        PartitionAlgo partitionAlgo = new PartitionAlgo(null);
        partitionAlgo.findSubPartition(ba, bb);
    }


    @Test
    public void doPartition(){
        HashSet<String> a = new HashSet<>();
        a.add("0");
        a.add("1");
        a.add("2");
        Block ba = new Block(a);

        HashSet<String> a2 = new HashSet<>();
        a2.add("3");
        a2.add("4");
        a2.add("5");
        Block ba2 = new Block(a2);

        Partition p = new Partition();
        p.addBlock(ba);
        p.addBlock(ba2);

        HashSet<String> b = new HashSet<>();
        b.add("1");
        b.add("4");
        Block bb = new Block(b);

        PartitionAlgo partitionAlgo = new PartitionAlgo(null);
        p = partitionAlgo.doPartition(p, bb);
        Assert.assertEquals(4, p.getBlocks().size());
    }


}
