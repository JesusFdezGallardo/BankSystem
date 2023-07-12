package org.example.daos;

import org.example.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferDao {

  Transfer create(Transfer transfer);

  Optional<Transfer> findById(Long id);

  List<Transfer> findTransfersFrom(String ibanAccountSource);

  List<Transfer> findTransfersTo(String ibanAccountDestination);
}
