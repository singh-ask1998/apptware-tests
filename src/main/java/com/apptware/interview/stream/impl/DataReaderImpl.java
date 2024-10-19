package com.apptware.interview.stream.impl;

import com.apptware.interview.stream.DataReader;
import com.apptware.interview.stream.PaginationService;
import jakarta.annotation.Nonnull;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DataReaderImpl implements DataReader {

  @Autowired private PaginationService paginationService;

  @Override
  public Stream<String> fetchLimitadData(int limit) {
    //passing limit so that only limited data is fetched from pagination service making this operation more efficient.
    return fetchPaginatedDataAsStream(limit).limit(limit);
  }

  @Override
  public Stream<String> fetchFullData() {
    //passing null so method fetches all data 
    return fetchPaginatedDataAsStream(null);
  }

  /**
   * This method is where the candidate should add the implementation. Logs have been added to track
   * the data fetching behavior. Do not modify any other areas of the code.
   */
  //adding one more attribute so that solution is more efficient in case of needing limited data.
  private @Nonnull Stream<String> fetchPaginatedDataAsStream(Integer limit) {
    log.info("Fetching paginated data as stream.");
       
    Stream<String> dataStream =  paginationService.getPaginatedData(1, limit!=null?limit:PaginationService.FULL_DATA_SIZE).stream();
    return dataStream.peek(item -> log.info("Fetched Item: {}", item));
  }
}
