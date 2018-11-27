package anishk.developer.teamratings.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Response<T> {

    @Builder.Default
    private int responseCode = 0;

    @Builder.Default
    private String responseDesc = "Operation Completed Successfully";

    private T responseObj;
}
