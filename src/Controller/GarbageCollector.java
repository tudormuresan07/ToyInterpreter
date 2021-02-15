package Controller;
;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector {
    static Map<Integer, IValue> safeGarbageCollector(List<Integer> addrList, Map<Integer,IValue> heap)
    {
        return heap.entrySet().stream()
                .filter(e->addrList.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    //static List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues)
    static List<Integer> getAddr(Collection<IValue> symTableValues)
    {
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v->{RefValue v1=(RefValue)v;return v1.getAddress();})
                .collect(Collectors.toList());
    }

    static List<Integer> getAllAddresses(List<Integer> symTableAddr,List<Integer> heapAddr)
    {
        return Stream
                .concat(symTableAddr.stream(),heapAddr.stream())
                .collect(Collectors.toList());
    }
}
