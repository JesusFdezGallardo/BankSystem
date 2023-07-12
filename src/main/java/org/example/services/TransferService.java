package org.example.services;

import org.example.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferService {

  Transfer issueTransfer(Long issuerId, String ibanAccountSource, String ibanAccountDestination, BigDecimal amount);

  List<Transfer> findTransfersFrom(String ibanAccountSource);

  List<Transfer> findTransfersTo(String ibanAccountDestination);
}
