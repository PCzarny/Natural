package xml_models;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _CesAnaChunkListChunkNsOrth_QNAME = new QName("", "orth");
    private final static QName _CesAnaChunkListChunkNsLex_QNAME = new QName("", "lex");

    public ObjectFactory() {
    }

    public CesAna createCesAna() {
        return new CesAna();
    }

    public CesAna.ChunkList createCesAnaChunkList() {
        return new CesAna.ChunkList();
    }

    public CesAna.ChunkList.Chunk createCesAnaChunkListChunk() {
        return new CesAna.ChunkList.Chunk();
    }

    public CesAna.ChunkList.Chunk.Ns createCesAnaChunkListChunkNs() {
        return new CesAna.ChunkList.Chunk.Ns();
    }

    public CesAna.ChunkList.Chunk.Tok createCesAnaChunkListChunkTok() {
        return new CesAna.ChunkList.Chunk.Tok();
    }

    public CesAna.ChunkList.Chunk.Ns.Lex createCesAnaChunkListChunkNsLex() {
        return new CesAna.ChunkList.Chunk.Ns.Lex();
    }

    public CesAna.ChunkList.Chunk.Tok.Lex createCesAnaChunkListChunkTokLex() {
        return new CesAna.ChunkList.Chunk.Tok.Lex();
    }

    @XmlElementDecl(namespace = "", name = "orth", scope = CesAna.ChunkList.Chunk.Ns.class)
    public JAXBElement<String> createCesAnaChunkListChunkNsOrth(String value) {
        return new JAXBElement<String>(_CesAnaChunkListChunkNsOrth_QNAME, String.class, CesAna.ChunkList.Chunk.Ns.class, value);
    }

    @XmlElementDecl(namespace = "", name = "lex", scope = CesAna.ChunkList.Chunk.Ns.class)
    public JAXBElement<CesAna.ChunkList.Chunk.Ns.Lex> createCesAnaChunkListChunkNsLex(CesAna.ChunkList.Chunk.Ns.Lex value) {
        return new JAXBElement<CesAna.ChunkList.Chunk.Ns.Lex>(_CesAnaChunkListChunkNsLex_QNAME, CesAna.ChunkList.Chunk.Ns.Lex.class, CesAna.ChunkList.Chunk.Ns.class, value);
    }

}
