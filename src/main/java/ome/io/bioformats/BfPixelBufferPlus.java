package ome.io.bioformats;

import java.io.IOException;

import loci.formats.FormatException;
import loci.formats.FormatTools;
import loci.formats.IFormatReader;
import ome.io.bioformats.BfPixelBuffer;

public class BfPixelBufferPlus extends BfPixelBuffer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    RGBInterleavedReader rgbInterleavedReader;

    public BfPixelBufferPlus(String filePath, IFormatReader bfReader)
            throws IOException, FormatException {
        super(filePath, bfReader);
        rgbInterleavedReader = null;
    }

    public BfPixelBufferPlus(String filePath, RGBInterleavedReader bfReader)
            throws IOException, FormatException {
        super(filePath, bfReader);
        rgbInterleavedReader = bfReader;
    }

    public byte[] getInterleavedTile(int x, int y, int w, int h) throws FormatException, IOException {
        int c = getSizeC() / bfReader.getEffectiveSizeC();
        c = 3;
        byte[] buf = new byte[w * h * FormatTools.getBytesPerPixel(reader().getPixelsType()) * c];
        return rgbInterleavedReader.openBytes(0, buf, x, y, w, h);
    }

}
