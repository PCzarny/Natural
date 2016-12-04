package xml_models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import xml_models.CesAna.ChunkList.Chunk.Ns;
import xml_models.CesAna.ChunkList.Chunk.Tok;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chunkList"
})
@XmlRootElement(name = "cesAna")
public class CesAna {

    @XmlElement(required = true)
    protected CesAna.ChunkList chunkList;
    @XmlAttribute(name = "version")
    protected Float version;
    @XmlAttribute(name = "type")
    protected String type;

    public CesAna.ChunkList getChunkList() {
        return chunkList;
    }
    
    public void setChunkList(CesAna.ChunkList value) {
        this.chunkList = value;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float value) {
        this.version = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "chunk"
    })
    public static class ChunkList {

        protected List<CesAna.ChunkList.Chunk> chunk;

        public List<CesAna.ChunkList.Chunk> getChunk() {
            if (chunk == null) {
                chunk = new ArrayList<CesAna.ChunkList.Chunk>();
            }
            return this.chunk;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tokOrNs"
        })
        public static class Chunk {
        	@XmlTransient
        	private List<Tok> tokList = new ArrayList<>();
        	@XmlTransient
 			private List<Ns> nsList = new ArrayList<>();

            @XmlElements({
                @XmlElement(name = "tok", type = CesAna.ChunkList.Chunk.Tok.class),
                @XmlElement(name = "ns", type = CesAna.ChunkList.Chunk.Ns.class)
            })
            protected List<Object> tokOrNs = new ArrayList<Object>() {
            	@Override
            	public boolean add(Object obj) {
    				if (obj instanceof Tok){
    					return tokList.add((Tok)obj);
    				}else{
    					return nsList.add((Ns)obj);
    				}
                }
            };
		
            @XmlAttribute(name = "type")
            protected String type;

            public List<Tok> getTokList() {
                if (tokList == null) {
                	tokList = new ArrayList<Tok>();
                }
                return this.tokList;
            }
            
            public List<Ns> getNsList() {
                if (nsList == null) {
                	nsList = new ArrayList<Ns>();
                }
                return this.nsList;
            }

            public String getType() {
                return type;
            }

            public void setType(String value) {
                this.type = value;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "content"
            })
            public static class Ns {

                @XmlElementRefs({
                    @XmlElementRef(name = "orth", type = JAXBElement.class, required = false),
                    @XmlElementRef(name = "lex", type = JAXBElement.class, required = false)
                })
                @XmlMixed
                protected List<Serializable> content;

                public List<Serializable> getContent() {
                    if (content == null) {
                        content = new ArrayList<Serializable>();
                    }
                    return this.content;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "base",
                    "ctag"
                })
                public static class Lex {

                    @XmlElement(required = true)
                    protected String base;
                    @XmlElement(required = true)
                    protected String ctag;
                    @XmlAttribute(name = "disamb")
                    protected Byte disamb;

                    public String getBase() {
                        return base;
                    }

                    public void setBase(String value) {
                        this.base = value;
                    }

                    public String getCtag() {
                        return ctag;
                    }

                    public void setCtag(String value) {
                        this.ctag = value;
                    }

                    public Byte getDisamb() {
                        return disamb;
                    }

                    public void setDisamb(Byte value) {
                        this.disamb = value;
                    }

                }

            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "orth",
                "lex"
            })
            public static class Tok {

                @XmlElement(required = true)
                protected String orth;
                @XmlTransient
                private Lex lexObject;
                protected List<CesAna.ChunkList.Chunk.Tok.Lex> lex = new ArrayList<CesAna.ChunkList.Chunk.Tok.Lex>() {
                	@Override
                	public boolean add(CesAna.ChunkList.Chunk.Tok.Lex obj) {
        				if (obj.getDisamb() != null){
        					Tok.this.lexObject = obj;
        				}
        				return super.add(obj);
                    }
                };

                public Lex getLex() {
                	return lexObject;
                }
                
                public String getOrth() {
                    return orth;
                }

                public void setOrth(String value) {
                    this.orth = value;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "base",
                    "ctag"
                })
                public static class Lex {

                    @XmlElement(required = true)
                    protected String base;
                    @XmlElement(required = true)
                    protected String ctag;
                    @XmlAttribute(name = "disamb")
                    protected Byte disamb;

                    public String getBase() {
                        return base;
                    }

                    public void setBase(String value) {
                        this.base = value;
                    }

                    public String getCtag() {
                        return ctag;
                    }

                    public void setCtag(String value) {
                        this.ctag = value;
                    }

                    public Byte getDisamb() {
                        return disamb;
                    }

                    public void setDisamb(Byte value) {
                        this.disamb = value;
                    }

                }

            }

        }

    }

}
