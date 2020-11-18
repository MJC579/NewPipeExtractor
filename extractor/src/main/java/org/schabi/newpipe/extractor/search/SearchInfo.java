package org.schabi.newpipe.extractor.search;

import org.schabi.newpipe.extractor.*;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.linkhandler.SearchQueryHandler;
import org.schabi.newpipe.extractor.utils.ExtractorHelper;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

public class SearchInfo extends ListInfo<InfoItem> {
    private String searchString;
    private String searchSuggestion;
    private boolean isCorrectedSearch;
    private List<MetaInfo> metaInfo;

    public SearchInfo(int serviceId,
                      SearchQueryHandler qIHandler,
                      String searchString) {
        super(serviceId, qIHandler, "Search");
        this.searchString = searchString;
    }


    public static SearchInfo getInfo(StreamingService service, SearchQueryHandler searchQuery) throws ExtractionException, IOException {
        SearchExtractor extractor = service.getSearchExtractor(searchQuery);
        extractor.fetchPage();
        return getInfo(extractor);
    }

    public static SearchInfo getInfo(SearchExtractor extractor) throws ExtractionException, IOException {
        final SearchInfo info = new SearchInfo(
                extractor.getServiceId(),
                extractor.getLinkHandler(),
                extractor.getSearchString());

        try {
            info.setOriginalUrl(extractor.getOriginalUrl());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setSearchSuggestion(extractor.getSearchSuggestion());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setIsCorrectedSearch(extractor.isCorrectedSearch());
        } catch (Exception e) {
            info.addError(e);
        }
        try {
            info.setMetaInfo(extractor.getMetaInfo());
        } catch (Exception e) {
            info.addError(e);
        }

        ListExtractor.InfoItemsPage<InfoItem> page = ExtractorHelper.getItemsPageOrLogError(info, extractor);
        info.setRelatedItems(page.getItems());
        info.setNextPage(page.getNextPage());

        return info;
    }


    public static ListExtractor.InfoItemsPage<InfoItem> getMoreItems(StreamingService service,
                                                                     SearchQueryHandler query,
                                                                     Page page)
            throws IOException, ExtractionException {
        return service.getSearchExtractor(query).getPage(page);
    }

    // Getter
    public String getSearchString() {
        return this.searchString;
    }

    public String getSearchSuggestion() {
        return this.searchSuggestion;
    }

    public boolean isCorrectedSearch() {
        return this.isCorrectedSearch;
    }

    public void setIsCorrectedSearch(boolean isCorrectedSearch) {
        this.isCorrectedSearch = isCorrectedSearch;
    }

    public void setSearchSuggestion(String searchSuggestion) {
        this.searchSuggestion = searchSuggestion;
    }

    @Nonnull
    public List<MetaInfo> getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(@Nonnull List<MetaInfo> metaInfo) {
        this.metaInfo = metaInfo;
    }
}
